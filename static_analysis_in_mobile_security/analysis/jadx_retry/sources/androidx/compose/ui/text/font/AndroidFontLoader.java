package androidx.compose.ui.text.font;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: compiled from: AndroidFontLoader.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\n \t*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/text/font/AndroidFontLoader;", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cacheKey", "", "getCacheKey", "()Ljava/lang/Object;", "kotlin.jvm.PlatformType", "awaitLoad", "Landroid/graphics/Typeface;", "font", "Landroidx/compose/ui/text/font/Font;", "(Landroidx/compose/ui/text/font/Font;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadBlocking", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidFontLoader implements PlatformFontLoader {
    private final Object cacheKey;
    private final Context context;

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.AndroidFontLoader$awaitLoad$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidFontLoader.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.text.font.AndroidFontLoader", f = "AndroidFontLoader.android.kt", i = {1, 1}, l = {LockFreeTaskQueueCore.CLOSED_SHIFT, 62}, m = "awaitLoad", n = {"this", "font"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidFontLoader.this.awaitLoad(null, this);
        }
    }

    public AndroidFontLoader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context.getApplicationContext();
    }

    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    public android.graphics.Typeface loadBlocking(Font font) {
        Object objM5563constructorimpl;
        android.graphics.Typeface typefaceLoad;
        Intrinsics.checkNotNullParameter(font, "font");
        if (font instanceof AndroidFont) {
            AndroidFont.TypefaceLoader typefaceLoader = ((AndroidFont) font).getTypefaceLoader();
            Context context = this.context;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            return typefaceLoader.loadBlocking(context, (AndroidFont) font);
        }
        if (!(font instanceof ResourceFont)) {
            return null;
        }
        int loadingStrategy = font.getLoadingStrategy();
        if (FontLoadingStrategy.m4854equalsimpl0(loadingStrategy, FontLoadingStrategy.INSTANCE.m4859getBlockingPKNRLFQ())) {
            Context context2 = this.context;
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            typefaceLoad = AndroidFontLoader_androidKt.load((ResourceFont) font, context2);
        } else if (FontLoadingStrategy.m4854equalsimpl0(loadingStrategy, FontLoadingStrategy.INSTANCE.m4860getOptionalLocalPKNRLFQ())) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AndroidFontLoader $this$loadBlocking_u24lambda_u240 = this;
                Context context3 = $this$loadBlocking_u24lambda_u240.context;
                Intrinsics.checkNotNullExpressionValue(context3, "context");
                objM5563constructorimpl = Result.m5563constructorimpl(AndroidFontLoader_androidKt.load((ResourceFont) font, context3));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM5563constructorimpl = Result.m5563constructorimpl(ResultKt.createFailure(th));
            }
            typefaceLoad = (android.graphics.Typeface) (Result.m5569isFailureimpl(objM5563constructorimpl) ? null : objM5563constructorimpl);
        } else {
            if (FontLoadingStrategy.m4854equalsimpl0(loadingStrategy, FontLoadingStrategy.INSTANCE.m4858getAsyncPKNRLFQ())) {
                throw new UnsupportedOperationException("Unsupported Async font load path");
            }
            throw new IllegalArgumentException("Unknown loading type " + ((Object) FontLoadingStrategy.m4856toStringimpl(font.getLoadingStrategy())));
        }
        FontVariation.Settings variationSettings = ((ResourceFont) font).getVariationSettings();
        Context context4 = this.context;
        Intrinsics.checkNotNullExpressionValue(context4, "context");
        return PlatformTypefacesKt.setFontVariationSettings(typefaceLoad, variationSettings, context4);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    public Object awaitLoad(Font font, Continuation<? super android.graphics.Typeface> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objLoadAsync;
        AndroidFontLoader androidFontLoader;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (font instanceof AndroidFont) {
                    AndroidFont.TypefaceLoader typefaceLoader = ((AndroidFont) font).getTypefaceLoader();
                    Context context = this.context;
                    Intrinsics.checkNotNullExpressionValue(context, "context");
                    anonymousClass2.label = 1;
                    Object objAwaitLoad = typefaceLoader.awaitLoad(context, (AndroidFont) font, anonymousClass2);
                    return objAwaitLoad == coroutine_suspended ? coroutine_suspended : objAwaitLoad;
                }
                if (!(font instanceof ResourceFont)) {
                    throw new IllegalArgumentException("Unknown font type: " + font);
                }
                Context context2 = this.context;
                Intrinsics.checkNotNullExpressionValue(context2, "context");
                anonymousClass2.L$0 = this;
                anonymousClass2.L$1 = font;
                anonymousClass2.label = 2;
                objLoadAsync = AndroidFontLoader_androidKt.loadAsync((ResourceFont) font, context2, anonymousClass2);
                if (objLoadAsync == coroutine_suspended) {
                    return coroutine_suspended;
                }
                androidFontLoader = this;
                break;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            case 2:
                font = (Font) anonymousClass2.L$1;
                androidFontLoader = (AndroidFontLoader) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                objLoadAsync = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        FontVariation.Settings variationSettings = ((ResourceFont) font).getVariationSettings();
        Context context3 = androidFontLoader.context;
        Intrinsics.checkNotNullExpressionValue(context3, "context");
        return PlatformTypefacesKt.setFontVariationSettings((android.graphics.Typeface) objLoadAsync, variationSettings, context3);
    }

    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    public Object getCacheKey() {
        return this.cacheKey;
    }
}
