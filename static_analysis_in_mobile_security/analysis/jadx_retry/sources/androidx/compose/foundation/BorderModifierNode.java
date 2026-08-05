package androidx.compose.foundation;

import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Border.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ,\u0010\u001e\u001a\u00020\u001f*\u00020 2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002JI\u0010'\u001a\u00020\u001f*\u00020 2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010!\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b-\u0010.R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R/\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006/"}, d2 = {"Landroidx/compose/foundation/BorderModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "widthParameter", "Landroidx/compose/ui/unit/Dp;", "brushParameter", "Landroidx/compose/ui/graphics/Brush;", "shapeParameter", "Landroidx/compose/ui/graphics/Shape;", "(FLandroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "borderCache", "Landroidx/compose/foundation/BorderCache;", "value", "brush", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "drawWithCacheModifierNode", "Landroidx/compose/ui/draw/CacheDrawModifierNode;", "shape", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "width", "getWidth-D9Ej5fM", "()F", "setWidth-0680j_4", "(F)V", "F", "drawGenericBorder", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "outline", "Landroidx/compose/ui/graphics/Outline$Generic;", "fillArea", "", "strokeWidth", "", "drawRoundRectBorder", "Landroidx/compose/ui/graphics/Outline$Rounded;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "borderSize", "Landroidx/compose/ui/geometry/Size;", "drawRoundRectBorder-JqoCqck", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Outline$Rounded;JJZF)Landroidx/compose/ui/draw/DrawResult;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BorderModifierNode extends DelegatingNode {
    private BorderCache borderCache;
    private Brush brush;
    private final CacheDrawModifierNode drawWithCacheModifierNode;
    private Shape shape;
    private float width;

    public /* synthetic */ BorderModifierNode(float f, Brush brush, Shape shape, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush, shape);
    }

    private BorderModifierNode(float widthParameter, Brush brushParameter, Shape shapeParameter) {
        Intrinsics.checkNotNullParameter(brushParameter, "brushParameter");
        Intrinsics.checkNotNullParameter(shapeParameter, "shapeParameter");
        this.width = widthParameter;
        this.brush = brushParameter;
        this.shape = shapeParameter;
        this.drawWithCacheModifierNode = (CacheDrawModifierNode) delegate(DrawModifierKt.CacheDrawModifierNode(new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.foundation.BorderModifierNode$drawWithCacheModifierNode$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DrawResult invoke(CacheDrawScope CacheDrawModifierNode) {
                Intrinsics.checkNotNullParameter(CacheDrawModifierNode, "$this$CacheDrawModifierNode");
                boolean hasValidBorderParams = CacheDrawModifierNode.mo327toPx0680j_4(this.this$0.getWidth()) >= 0.0f && Size.m2799getMinDimensionimpl(CacheDrawModifierNode.m2637getSizeNHjbRc()) > 0.0f;
                if (!hasValidBorderParams) {
                    return BorderKt.drawContentWithoutBorder(CacheDrawModifierNode);
                }
                float f = 2;
                float strokeWidthPx = Math.min(Dp.m5279equalsimpl0(this.this$0.getWidth(), Dp.INSTANCE.m5292getHairlineD9Ej5fM()) ? 1.0f : (float) Math.ceil(CacheDrawModifierNode.mo327toPx0680j_4(this.this$0.getWidth())), (float) Math.ceil(Size.m2799getMinDimensionimpl(CacheDrawModifierNode.m2637getSizeNHjbRc()) / f));
                float halfStroke = strokeWidthPx / f;
                long topLeft = OffsetKt.Offset(halfStroke, halfStroke);
                long borderSize = SizeKt.Size(Size.m2800getWidthimpl(CacheDrawModifierNode.m2637getSizeNHjbRc()) - strokeWidthPx, Size.m2797getHeightimpl(CacheDrawModifierNode.m2637getSizeNHjbRc()) - strokeWidthPx);
                boolean fillArea = f * strokeWidthPx > Size.m2799getMinDimensionimpl(CacheDrawModifierNode.m2637getSizeNHjbRc());
                Outline outline = this.this$0.getShape().mo209createOutlinePq9zytI(CacheDrawModifierNode.m2637getSizeNHjbRc(), CacheDrawModifierNode.getLayoutDirection(), CacheDrawModifierNode);
                if (outline instanceof Outline.Generic) {
                    BorderModifierNode borderModifierNode = this.this$0;
                    return borderModifierNode.drawGenericBorder(CacheDrawModifierNode, borderModifierNode.getBrush(), (Outline.Generic) outline, fillArea, strokeWidthPx);
                }
                if (outline instanceof Outline.Rounded) {
                    BorderModifierNode borderModifierNode2 = this.this$0;
                    return borderModifierNode2.m177drawRoundRectBorderJqoCqck(CacheDrawModifierNode, borderModifierNode2.getBrush(), (Outline.Rounded) outline, topLeft, borderSize, fillArea, strokeWidthPx);
                }
                if (outline instanceof Outline.Rectangle) {
                    return BorderKt.m174drawRectBorderNsqcLGU(CacheDrawModifierNode, this.this$0.getBrush(), topLeft, borderSize, fillArea, strokeWidthPx);
                }
                throw new NoWhenBranchMatchedException();
            }
        }));
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: setWidth-0680j_4, reason: not valid java name */
    public final void m179setWidth0680j_4(float value) {
        if (!Dp.m5279equalsimpl0(this.width, value)) {
            this.width = value;
            this.drawWithCacheModifierNode.invalidateDrawCache();
        }
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final void setBrush(Brush value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!Intrinsics.areEqual(this.brush, value)) {
            this.brush = value;
            this.drawWithCacheModifierNode.invalidateDrawCache();
        }
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final void setShape(Shape value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!Intrinsics.areEqual(this.shape, value)) {
            this.shape = value;
            this.drawWithCacheModifierNode.invalidateDrawCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x00ef  */
    /* JADX WARN: Type inference failed for: r40v2, types: [T, androidx.compose.ui.graphics.ImageBitmap] */
    public final DrawResult drawGenericBorder(CacheDrawScope $this$drawGenericBorder, final Brush brush, final Outline.Generic outline, boolean fillArea, float strokeWidth) {
        int config;
        ColorFilter colorFilter;
        boolean z;
        BorderCache this_$iv;
        ImageBitmap targetImageBitmap$iv;
        Canvas targetCanvas$iv;
        if (fillArea) {
            return $this$drawGenericBorder.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode.drawGenericBorder.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                    invoke2(contentDrawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ContentDrawScope onDrawWithContent) {
                    Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
                    onDrawWithContent.drawContent();
                    DrawScope.CC.m3517drawPathGBMwjPU$default(onDrawWithContent, outline.getPath(), brush, 0.0f, null, null, 0, 60, null);
                }
            });
        }
        if (brush instanceof SolidColor) {
            int config2 = ImageBitmapConfig.INSTANCE.m3193getAlpha8_sVssgQ();
            ColorFilter colorFilter2 = ColorFilter.Companion.m3012tintxETnrds$default(ColorFilter.INSTANCE, ((SolidColor) brush).getValue(), 0, 2, null);
            config = config2;
            colorFilter = colorFilter2;
        } else {
            int config3 = ImageBitmapConfig.INSTANCE.m3194getArgb8888_sVssgQ();
            config = config3;
            colorFilter = null;
        }
        final Rect pathBounds = outline.getPath().getBounds();
        if (this.borderCache == null) {
            this.borderCache = new BorderCache(null, null, null, null, 15, null);
        }
        BorderCache borderCache = this.borderCache;
        Intrinsics.checkNotNull(borderCache);
        Path maskPath = borderCache.obtainPath();
        maskPath.reset();
        maskPath.addRect(pathBounds);
        maskPath.mo2865opN5in7k0(maskPath, outline.getPath(), PathOperation.INSTANCE.m3263getDifferenceb3I0S0c());
        final Ref.ObjectRef cacheImageBitmap = new Ref.ObjectRef();
        final long pathBoundsSize = IntSizeKt.IntSize((int) Math.ceil(pathBounds.getWidth()), (int) Math.ceil(pathBounds.getHeight()));
        BorderCache $this$drawGenericBorder_u24lambda_u244 = this.borderCache;
        Intrinsics.checkNotNull($this$drawGenericBorder_u24lambda_u244);
        ImageBitmap targetImageBitmap$iv2 = $this$drawGenericBorder_u24lambda_u244.imageBitmap;
        Canvas targetCanvas$iv2 = $this$drawGenericBorder_u24lambda_u244.canvas;
        ImageBitmapConfig imageBitmapConfigM3186boximpl = targetImageBitmap$iv2 != null ? ImageBitmapConfig.m3186boximpl(targetImageBitmap$iv2.mo2840getConfig_sVssgQ()) : null;
        if (!(imageBitmapConfigM3186boximpl == null ? false : ImageBitmapConfig.m3189equalsimpl0(imageBitmapConfigM3186boximpl.m3192unboximpl(), ImageBitmapConfig.INSTANCE.m3194getArgb8888_sVssgQ()))) {
            z = ImageBitmapConfig.m3188equalsimpl(config, targetImageBitmap$iv2 != null ? ImageBitmapConfig.m3186boximpl(targetImageBitmap$iv2.mo2840getConfig_sVssgQ()) : null);
        }
        boolean compatibleConfig$iv = z;
        if (targetImageBitmap$iv2 == null || targetCanvas$iv2 == null || Size.m2800getWidthimpl($this$drawGenericBorder.m2637getSizeNHjbRc()) > targetImageBitmap$iv2.getWidth() || Size.m2797getHeightimpl($this$drawGenericBorder.m2637getSizeNHjbRc()) > targetImageBitmap$iv2.getHeight() || !compatibleConfig$iv) {
            this_$iv = $this$drawGenericBorder_u24lambda_u244;
            ImageBitmap it$iv = ImageBitmapKt.m3199ImageBitmapx__hDU$default(IntSize.m5434getWidthimpl(pathBoundsSize), IntSize.m5433getHeightimpl(pathBoundsSize), config, false, null, 24, null);
            this_$iv.imageBitmap = it$iv;
            Canvas it$iv2 = androidx.compose.ui.graphics.CanvasKt.Canvas(it$iv);
            this_$iv.canvas = it$iv2;
            targetImageBitmap$iv = it$iv;
            targetCanvas$iv = it$iv2;
        } else {
            this_$iv = $this$drawGenericBorder_u24lambda_u244;
            targetCanvas$iv = targetCanvas$iv2;
            targetImageBitmap$iv = targetImageBitmap$iv2;
        }
        CanvasDrawScope it$iv3 = this_$iv.canvasDrawScope;
        if (it$iv3 == null) {
            it$iv3 = new CanvasDrawScope();
            this_$iv.canvasDrawScope = it$iv3;
        }
        CanvasDrawScope targetDrawScope$iv = it$iv3;
        long drawSize$iv = IntSizeKt.m5444toSizeozmzZPI(pathBoundsSize);
        LayoutDirection layoutDirection$iv$iv = $this$drawGenericBorder.getLayoutDirection();
        CanvasDrawScope.DrawParams drawParams = targetDrawScope$iv.getDrawParams();
        Density prevDensity$iv$iv = drawParams.getDensity();
        LayoutDirection prevLayoutDirection$iv$iv = drawParams.getLayoutDirection();
        Canvas prevCanvas$iv$iv = drawParams.getCanvas();
        long prevSize$iv$iv = drawParams.getSize();
        CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u240$iv$iv = targetDrawScope$iv.getDrawParams();
        $this$draw_yzxVdVo_u24lambda_u240$iv$iv.setDensity($this$drawGenericBorder);
        $this$draw_yzxVdVo_u24lambda_u240$iv$iv.setLayoutDirection(layoutDirection$iv$iv);
        $this$draw_yzxVdVo_u24lambda_u240$iv$iv.setCanvas(targetCanvas$iv);
        $this$draw_yzxVdVo_u24lambda_u240$iv$iv.m3447setSizeuvyYCjk(drawSize$iv);
        targetCanvas$iv.save();
        CanvasDrawScope $this$drawBorderCache_EMwLDEs_u24lambda_u243$iv = targetDrawScope$iv;
        DrawScope.CC.m3522drawRectnJ9OG0$default($this$drawBorderCache_EMwLDEs_u24lambda_u243$iv, Color.INSTANCE.m2997getBlack0d7_KjU(), 0L, drawSize$iv, 0.0f, null, null, BlendMode.INSTANCE.m2888getClear0nO6VwU(), 58, null);
        float left$iv = -pathBounds.getLeft();
        float top$iv = -pathBounds.getTop();
        $this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
        ?? r40 = targetImageBitmap$iv;
        DrawScope.CC.m3517drawPathGBMwjPU$default($this$drawBorderCache_EMwLDEs_u24lambda_u243$iv, outline.getPath(), brush, 0.0f, new Stroke(strokeWidth * 2, 0.0f, 0, 0, null, 30, null), null, 0, 52, null);
        float f = 1;
        float scaleX$iv = (Size.m2800getWidthimpl($this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.mo3442getSizeNHjbRc()) + f) / Size.m2800getWidthimpl($this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.mo3442getSizeNHjbRc());
        float scaleY$iv = (Size.m2797getHeightimpl($this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.mo3442getSizeNHjbRc()) + f) / Size.m2797getHeightimpl($this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.mo3442getSizeNHjbRc());
        long pivot$iv = $this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.mo3441getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo3448getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
        DrawTransform $this$scale_Fgt4K4Q_u24lambda_u242$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
        $this$scale_Fgt4K4Q_u24lambda_u242$iv.mo3455scale0AR0LA0(scaleX$iv, scaleY$iv, pivot$iv);
        DrawScope.CC.m3517drawPathGBMwjPU$default($this$drawBorderCache_EMwLDEs_u24lambda_u243$iv, maskPath, brush, 0.0f, null, null, BlendMode.INSTANCE.m2888getClear0nO6VwU(), 28, null);
        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
        $this$withTransform_u24lambda_u246$iv$iv.mo3449setSizeuvyYCjk(previousSize$iv$iv);
        $this$drawBorderCache_EMwLDEs_u24lambda_u243$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
        targetCanvas$iv.restore();
        CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u241$iv$iv = targetDrawScope$iv.getDrawParams();
        $this$draw_yzxVdVo_u24lambda_u241$iv$iv.setDensity(prevDensity$iv$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv$iv.setLayoutDirection(prevLayoutDirection$iv$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv$iv.setCanvas(prevCanvas$iv$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv$iv.m3447setSizeuvyYCjk(prevSize$iv$iv);
        r40.prepareToDraw();
        cacheImageBitmap.element = r40;
        final ColorFilter colorFilter3 = colorFilter;
        return $this$drawGenericBorder.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode.drawGenericBorder.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope onDrawWithContent) {
                Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
                onDrawWithContent.drawContent();
                ContentDrawScope $this$translate$iv = onDrawWithContent;
                float left$iv2 = pathBounds.getLeft();
                float top$iv2 = pathBounds.getTop();
                Ref.ObjectRef<ImageBitmap> objectRef = cacheImageBitmap;
                long j = pathBoundsSize;
                ColorFilter colorFilter4 = colorFilter3;
                $this$translate$iv.getDrawContext().getTransform().translate(left$iv2, top$iv2);
                DrawScope.CC.m3511drawImageAZ2fEMs$default($this$translate$iv, objectRef.element, 0L, j, 0L, 0L, 0.0f, null, colorFilter4, 0, 0, 890, null);
                $this$translate$iv.getDrawContext().getTransform().translate(-left$iv2, -top$iv2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawRoundRectBorder-JqoCqck, reason: not valid java name */
    public final DrawResult m177drawRoundRectBorderJqoCqck(CacheDrawScope $this$drawRoundRectBorder_u2dJqoCqck, final Brush brush, Outline.Rounded outline, final long topLeft, final long borderSize, final boolean fillArea, final float strokeWidth) {
        if (RoundRectKt.isSimple(outline.getRoundRect())) {
            final long cornerRadius = outline.getRoundRect().m2781getTopLeftCornerRadiuskKHJgLs();
            final float halfStroke = strokeWidth / 2;
            final Stroke borderStroke = new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null);
            return $this$drawRoundRectBorder_u2dJqoCqck.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                    invoke2(contentDrawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ContentDrawScope onDrawWithContent) {
                    Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
                    onDrawWithContent.drawContent();
                    if (fillArea) {
                        DrawScope.CC.m3523drawRoundRectZuiqVtQ$default(onDrawWithContent, brush, 0L, 0L, cornerRadius, 0.0f, null, null, 0, 246, null);
                        return;
                    }
                    float fM2706getXimpl = CornerRadius.m2706getXimpl(cornerRadius);
                    float f = halfStroke;
                    if (fM2706getXimpl < f) {
                        ContentDrawScope $this$clipRect_u2drOu3jXo$iv = onDrawWithContent;
                        float left$iv = strokeWidth;
                        float right$iv = Size.m2800getWidthimpl(onDrawWithContent.mo3442getSizeNHjbRc()) - strokeWidth;
                        float bottom$iv = Size.m2797getHeightimpl(onDrawWithContent.mo3442getSizeNHjbRc()) - strokeWidth;
                        int clipOp$iv = ClipOp.INSTANCE.m2959getDifferencertfAjoo();
                        Brush brush2 = brush;
                        long j = cornerRadius;
                        DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo$iv.getDrawContext();
                        long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo3448getSizeNHjbRc();
                        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
                        DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                        $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo3451clipRectN_I0leg(left$iv, left$iv, right$iv, bottom$iv, clipOp$iv);
                        DrawScope.CC.m3523drawRoundRectZuiqVtQ$default($this$clipRect_u2drOu3jXo$iv, brush2, 0L, 0L, j, 0.0f, null, null, 0, 246, null);
                        $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                        $this$withTransform_u24lambda_u246$iv$iv.mo3449setSizeuvyYCjk(previousSize$iv$iv);
                        return;
                    }
                    DrawScope.CC.m3523drawRoundRectZuiqVtQ$default(onDrawWithContent, brush, topLeft, borderSize, BorderKt.m175shrinkKibmq7A(cornerRadius, f), 0.0f, borderStroke, null, 0, 208, null);
                }
            });
        }
        if (this.borderCache == null) {
            this.borderCache = new BorderCache(null, null, null, null, 15, null);
        }
        BorderCache borderCache = this.borderCache;
        Intrinsics.checkNotNull(borderCache);
        Path path = borderCache.obtainPath();
        final Path roundedRectPath = BorderKt.createRoundRectPath(path, outline.getRoundRect(), strokeWidth, fillArea);
        return $this$drawRoundRectBorder_u2dJqoCqck.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope onDrawWithContent) {
                Intrinsics.checkNotNullParameter(onDrawWithContent, "$this$onDrawWithContent");
                onDrawWithContent.drawContent();
                DrawScope.CC.m3517drawPathGBMwjPU$default(onDrawWithContent, roundedRectPath, brush, 0.0f, null, null, 0, 60, null);
            }
        });
    }
}
