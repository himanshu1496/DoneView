package io.github.himanshu1496.doneview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class DoneView extends View {

    Paint mCirclePaint;
    Paint mIconPaint;
    float mIconPadding;
    float mRadius;

    PathMeasure mCircleMeasure;
    PathMeasure mIconMeasure;
    float mCircleLength;
    float mIconLength;

    int mRepeatCount;

    Point mCenter = new Point(0, 0);
    ValueAnimator mAnimator;
    float mAnimationOffset = 0;

    public DoneView(Context context) {
        super(context);
    }

    public DoneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.DoneView, 0, 0);

        mCirclePaint = new Paint();
        mCirclePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(array.getColor(R.styleable.DoneView_circleStrokeColor, Color.BLUE));
        mCirclePaint.setStrokeWidth(array.getDimension(R.styleable.DoneView_circleStrokeWidth, 5f));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mCirclePaint.setStrokeJoin(Paint.Join.ROUND);

        mIconPaint = new Paint();
        mIconPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mIconPaint.setColor(array.getColor(R.styleable.DoneView_iconStrokeColor, Color.MAGENTA));
        mIconPaint.setStrokeWidth(array.getDimension(R.styleable.DoneView_iconStrokeWidth, 5f));
        mIconPaint.setStyle(Paint.Style.STROKE);
        mIconPaint.setStrokeCap(Paint.Cap.ROUND);
        mIconPaint.setStrokeJoin(Paint.Join.ROUND);

        mIconPadding = array.getDimension(R.styleable.DoneView_iconPadding, 20);
        mRepeatCount = array.getInteger(R.styleable.DoneView_repeatCount, -1);
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenter.set(w/2, h/2);
        RectF primaryOval = new RectF();
        int radius = (Math.min(w, h))/4;
        primaryOval.set(w/2 - radius, h/2 - radius, w/2 + radius, h/2 + radius);
        mRadius = (int) (radius - mIconPadding);

        Path iconPath = drawDonePath(mRadius, new Path());
        mIconMeasure = new PathMeasure(iconPath, false);
        mIconLength = mIconMeasure.getLength();

        Path mCirclePath = drawCirclePath(new Path(), primaryOval);
        mCircleMeasure = new PathMeasure(mCirclePath, false);
        mCircleLength = mCircleMeasure.getLength();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float percentage = mAnimationOffset/100;

        Path circlePartialPath = new Path();
        mCircleMeasure.getSegment(0.0f, (mCircleLength * percentage), circlePartialPath, true);
        canvas.drawPath(circlePartialPath, mCirclePaint);


        Path partialPath = new Path();
        mIconMeasure.getSegment(0.0f, (mIconLength * percentage), partialPath, true);
        canvas.drawPath(partialPath, mIconPaint);
    }

    private Path drawDonePath(float radius, Path path){
        if (path == null) {
            path = new Path();
        }
        path.reset();
        double angleInRadians = Math.PI / 4;
        float centerX = mCenter.x + radius/4;
        float centerY = mCenter.y;

        float nextPointX = Double.valueOf( centerX- (radius / Math.cos(angleInRadians))).floatValue();
        float nextPointY = centerY;
        path.moveTo(nextPointX, nextPointY);

        nextPointX = (Double.valueOf(centerX - (radius * Math.cos(angleInRadians)))).floatValue();
        nextPointY =  (Double.valueOf(centerY + (radius * Math.sin(angleInRadians)))).floatValue();

        path.lineTo(nextPointX, nextPointY);

        nextPointX = Double.valueOf(centerX + (radius * Math.cos(angleInRadians))).floatValue();
        nextPointY = Double.valueOf(centerY - (radius * Math.sin(angleInRadians))).floatValue();

        path.lineTo(nextPointX, nextPointY);
        return path;
    }

    private Path drawCirclePath(Path path, RectF primaryOval){
        if (path == null) {
            path = new Path();
        }
        path.reset();

        int angle = 270;
        path.addArc(primaryOval, angle, 359);
        return path;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void startAnimation() {
        mAnimationOffset = 0;
        mAnimator = ValueAnimator.ofFloat(0, 100);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimationOffset = (float) valueAnimator.getAnimatedValue();
                postInvalidateOnAnimation();
            }
        });
        mAnimator.setDuration(2000);
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        if (mRepeatCount == -1) {
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        } else {
            mAnimator.setRepeatCount(mRepeatCount - 1);
        }
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.start();
    }

    public void stopAnimation() {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
        mAnimationOffset = 100;
        postInvalidate();
    }

    public void setmRepeatCount(int mRepeatCount) {
        this.mRepeatCount = mRepeatCount;
    }

    public void setCircleColor(int color) {
        mCirclePaint.setColor(color);
    }

    public void setIconColor(int color) {
        mIconPaint.setColor(color);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }
}
