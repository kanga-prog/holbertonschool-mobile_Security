package androidx.compose.ui.tooling;

import androidx.compose.runtime.Composer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ComposableInvoker.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(message = "Use androidx.compose.runtime.reflect.ComposableMethodInvoker instead")
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002J1\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\fH\u0002¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J=\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0019J(\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\f\"\u0006\b\u0000\u0010\u001b\u0018\u0001*\u0002H\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0082\b¢\u0006\u0002\u0010\u001dJ5\u0010\u001e\u001a\u00020\u001f*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0015\u001a\u00020\u00142\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010 J9\u0010!\u001a\u00020\u001f*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0015\u001a\u00020\u00142\u001a\u0010\u0018\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\r0\f\"\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0002\u0010\"J\u0012\u0010#\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\rH\u0002J=\u0010$\u001a\u0004\u0018\u00010\u0001*\u00020\u001f2\b\u0010%\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/compose/ui/tooling/ComposableInvoker;", "", "()V", "BITS_PER_INT", "", "SLOTS_PER_INT", "changedParamCount", "realValueParams", "thisParams", "compatibleTypes", "", "methodTypes", "", "Ljava/lang/Class;", "actualTypes", "([Ljava/lang/Class;[Ljava/lang/Class;)Z", "defaultParamCount", "invokeComposable", "", "className", "", "methodName", "composer", "Landroidx/compose/runtime/Composer;", "args", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;[Ljava/lang/Object;)V", "dup", "T", "count", "(Ljava/lang/Object;I)[Ljava/lang/Object;", "findComposableMethod", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/reflect/Method;", "getDeclaredCompatibleMethod", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", "getDefaultValue", "invokeComposableMethod", "instance", "(Ljava/lang/reflect/Method;Ljava/lang/Object;Landroidx/compose/runtime/Composer;[Ljava/lang/Object;)Ljava/lang/Object;", "ui-tooling_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ComposableInvoker {
    public static final int $stable = 0;
    private static final int BITS_PER_INT = 31;
    public static final ComposableInvoker INSTANCE = new ComposableInvoker();
    private static final int SLOTS_PER_INT = 10;

    private ComposableInvoker() {
    }

    private final boolean compatibleTypes(Class<?>[] methodTypes, Class<?>[] actualTypes) {
        boolean z;
        if (methodTypes.length != actualTypes.length) {
            return false;
        }
        Collection destination$iv$iv = new ArrayList(methodTypes.length);
        int index$iv$iv = 0;
        int length = methodTypes.length;
        int i = 0;
        while (i < length) {
            destination$iv$iv.add(Boolean.valueOf(methodTypes[i].isAssignableFrom(actualTypes[index$iv$iv])));
            i++;
            index$iv$iv++;
        }
        Iterable $this$all$iv = (List) destination$iv$iv;
        if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
            z = true;
        } else {
            for (Object element$iv : $this$all$iv) {
                boolean it = ((Boolean) element$iv).booleanValue();
                if (!it) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0063  */
    /* JADX WARN: Code duplicated, block: B:12:0x0065  */
    /* JADX WARN: Code duplicated, block: B:9:0x0051  */
    private final Method getDeclaredCompatibleMethod(Class<?> cls, String methodName, Class<?>... clsArr) throws NoSuchMethodException {
        Object obj;
        ComposableInvoker composableInvoker;
        Class<?>[] parameterTypes;
        boolean z;
        Class<?>[] clsArr2 = (Class[]) Arrays.copyOf(clsArr, clsArr.length);
        Object[] declaredMethods = cls.getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaredMethods");
        Object[] $this$firstOrNull$iv = declaredMethods;
        int length = $this$firstOrNull$iv.length;
        int i = 0;
        while (true) {
            obj = null;
            if (i >= length) {
                break;
            }
            Object element$iv = $this$firstOrNull$iv[i];
            Method it = (Method) element$iv;
            if (!Intrinsics.areEqual(methodName, it.getName())) {
                String name = it.getName();
                Intrinsics.checkNotNullExpressionValue(name, "it.name");
                if (StringsKt.startsWith$default(name, methodName + '-', false, 2, (Object) null)) {
                    composableInvoker = INSTANCE;
                    parameterTypes = it.getParameterTypes();
                    Intrinsics.checkNotNullExpressionValue(parameterTypes, "it.parameterTypes");
                    if (composableInvoker.compatibleTypes(parameterTypes, clsArr2)) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
            } else {
                composableInvoker = INSTANCE;
                parameterTypes = it.getParameterTypes();
                Intrinsics.checkNotNullExpressionValue(parameterTypes, "it.parameterTypes");
                if (composableInvoker.compatibleTypes(parameterTypes, clsArr2)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                obj = element$iv;
                break;
            }
            i++;
        }
        Method method = (Method) obj;
        if (method != null) {
            return method;
        }
        throw new NoSuchMethodException(methodName + " not found");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final /* synthetic */ <T> T[] dup(T t, int i) {
        IntRange intRangeUntil = RangesKt.until(0, i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(t);
        }
        Intrinsics.reifiedOperationMarker(0, "T?");
        return (T[]) arrayList.toArray(new Object[0]);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0111 A[Catch: ReflectiveOperationException -> 0x011a, LOOP:2: B:26:0x00d1->B:40:0x0111, LOOP_END, TryCatch #3 {ReflectiveOperationException -> 0x011a, blocks: (B:31:0x0102, B:42:0x0116, B:40:0x0111), top: B:61:0x0102 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0122  */
    /* JADX WARN: Code duplicated, block: B:53:0x0124  */
    /* JADX WARN: Code duplicated, block: B:67:0x0110 A[SYNTHETIC] */
    private final Method findComposableMethod(Class<?> cls, String methodName, Object... args) throws NoSuchMethodException {
        Method method;
        Method declaredCompatibleMethod;
        Method method2;
        boolean z;
        try {
            int changedParams = changedParamCount(args.length, 0);
            SpreadBuilder spreadBuilder = new SpreadBuilder(3);
            Collection destination$iv$iv = new ArrayList();
            int length = args.length;
            for (int i = 0; i < length; i++) {
                Object element$iv$iv$iv = args[i];
                Class<?> cls2 = element$iv$iv$iv != null ? element$iv$iv$iv.getClass() : null;
                if (cls2 != null) {
                    destination$iv$iv.add(cls2);
                }
            }
            Collection $this$toTypedArray$iv = (List) destination$iv$iv;
            spreadBuilder.addSpread($this$toTypedArray$iv.toArray(new Class[0]));
            spreadBuilder.add(Composer.class);
            Class cls3 = Integer.TYPE;
            Iterable $this$map$iv$iv = RangesKt.until(0, changedParams);
            Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
            Iterator<Integer> it = $this$map$iv$iv.iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                destination$iv$iv$iv.add(cls3);
            }
            Collection thisCollection$iv$iv = (List) destination$iv$iv$iv;
            spreadBuilder.addSpread(thisCollection$iv$iv.toArray(new Class[0]));
            try {
                declaredCompatibleMethod = getDeclaredCompatibleMethod(cls, methodName, (Class[]) spreadBuilder.toArray(new Class[spreadBuilder.size()]));
            } catch (ReflectiveOperationException e) {
                e = e;
                try {
                    Method[] declaredMethods = cls.getDeclaredMethods();
                    Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaredMethods");
                    Method[] methodArr = declaredMethods;
                    int length2 = methodArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            method = null;
                            method2 = null;
                            break;
                        }
                        method2 = methodArr[i2];
                        Method it2 = method2;
                        if (Intrinsics.areEqual(it2.getName(), methodName)) {
                            method = null;
                        } else {
                            String name = it2.getName();
                            Intrinsics.checkNotNullExpressionValue(name, "it.name");
                            method = null;
                            try {
                                if (!StringsKt.startsWith$default(name, methodName + '-', false, 2, (Object) null)) {
                                    z = false;
                                }
                                if (z) {
                                    break;
                                }
                                i2++;
                            } catch (ReflectiveOperationException e2) {
                                declaredCompatibleMethod = method;
                                if (declaredCompatibleMethod != null) {
                                    throw new NoSuchMethodException(cls.getName() + '.' + methodName);
                                }
                                Method method3 = declaredCompatibleMethod;
                                return method3;
                            }
                        }
                        z = true;
                        if (z) {
                            break;
                            break;
                        }
                        i2++;
                    }
                    method = method2;
                } catch (ReflectiveOperationException e3) {
                    method = null;
                }
                declaredCompatibleMethod = method;
            }
        } catch (ReflectiveOperationException e4) {
            e = e4;
        }
        if (declaredCompatibleMethod != null) {
            throw new NoSuchMethodException(cls.getName() + '.' + methodName);
        }
        Method method4 = declaredCompatibleMethod;
        return method4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Object getDefaultValue(Class<?> cls) {
        String name = cls.getName();
        if (name != null) {
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.valueOf(0.0d);
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return 0;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return (byte) 0;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return (char) 0;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return 0L;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return false;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.valueOf(0.0f);
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return (short) 0;
                    }
                    break;
            }
        }
        return null;
    }

    private final Object invokeComposableMethod(Method $this$invokeComposableMethod, Object instance, Composer composer, Object... args) {
        int realParams;
        Object defaultValue;
        Object[] parameterTypes = $this$invokeComposableMethod.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "parameterTypes");
        Object[] $this$indexOfLast$iv = parameterTypes;
        int i = -1;
        int length = $this$indexOfLast$iv.length - 1;
        if (length >= 0) {
            do {
                int index$iv = length;
                length--;
                if (Intrinsics.areEqual((Class) $this$indexOfLast$iv[index$iv], Composer.class)) {
                    i = index$iv;
                    break;
                }
            } while (length >= 0);
        }
        int composerIndex = i;
        int realParams2 = composerIndex;
        int thisParams = instance != null ? 1 : 0;
        int changedParams = changedParamCount(realParams2, thisParams);
        int totalParamsWithoutDefaults = realParams2 + 1 + changedParams;
        int totalParams = $this$invokeComposableMethod.getParameterTypes().length;
        boolean isDefault = totalParams != totalParamsWithoutDefaults;
        int defaultParams = isDefault ? defaultParamCount(realParams2) : 0;
        if (!(((realParams2 + 1) + changedParams) + defaultParams == totalParams)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        int changedStartIndex = composerIndex + 1;
        int defaultStartIndex = changedStartIndex + changedParams;
        Object[] arguments = new Object[totalParams];
        int i2 = 0;
        while (i2 < totalParams) {
            if (!(i2 >= 0 && i2 < realParams2)) {
                realParams = realParams2;
                if (i2 == composerIndex) {
                    defaultValue = composer;
                } else {
                    if (changedStartIndex <= i2 && i2 < defaultStartIndex) {
                        defaultValue = 0;
                    } else {
                        if (!(defaultStartIndex <= i2 && i2 < totalParams)) {
                            throw new IllegalStateException("Unexpected index".toString());
                        }
                        defaultValue = 2097151;
                    }
                }
            } else if (i2 < 0 || i2 > ArraysKt.getLastIndex(args)) {
                ComposableInvoker composableInvoker = INSTANCE;
                Class<?> cls = $this$invokeComposableMethod.getParameterTypes()[i2];
                realParams = realParams2;
                Intrinsics.checkNotNullExpressionValue(cls, "parameterTypes[idx]");
                defaultValue = composableInvoker.getDefaultValue(cls);
            } else {
                defaultValue = args[i2];
                realParams = realParams2;
            }
            arguments[i2] = defaultValue;
            i2++;
            realParams2 = realParams;
        }
        return $this$invokeComposableMethod.invoke(instance, Arrays.copyOf(arguments, arguments.length));
    }

    private final int changedParamCount(int realValueParams, int thisParams) {
        if (realValueParams == 0) {
            return 1;
        }
        int totalParams = realValueParams + thisParams;
        return (int) Math.ceil(((double) totalParams) / 10.0d);
    }

    private final int defaultParamCount(int realValueParams) {
        return (int) Math.ceil(((double) realValueParams) / 31.0d);
    }

    public final void invokeComposable(String className, String methodName, Composer composer, Object... args) throws ReflectiveOperationException {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(composer, "composer");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            Class<?> composableClass = Class.forName(className);
            Intrinsics.checkNotNullExpressionValue(composableClass, "composableClass");
            Method method = findComposableMethod(composableClass, methodName, Arrays.copyOf(args, args.length));
            method.setAccessible(true);
            if (Modifier.isStatic(method.getModifiers())) {
                invokeComposableMethod(method, null, composer, Arrays.copyOf(args, args.length));
            } else {
                Object instance = composableClass.getConstructor(new Class[0]).newInstance(new Object[0]);
                invokeComposableMethod(method, instance, composer, Arrays.copyOf(args, args.length));
            }
        } catch (ReflectiveOperationException e) {
            PreviewLogger.Companion.logWarning$ui_tooling_release$default(PreviewLogger.INSTANCE, "Failed to invoke Composable Method '" + className + '.' + methodName + '\'', null, 2, null);
            throw e;
        }
    }
}
