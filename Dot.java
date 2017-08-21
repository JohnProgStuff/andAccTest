package com.mycompany.myapp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Scroller;

import com.mycompany.myapp.R;

import java.lang.Math;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;


/**
 * Custom view that shows a movable Dot.
 */
public class Dot extends ViewGroup {
    
    private Paint mDotPaint;
    
    private float mDotRadius = 2.0f;
    private float mDotX;
    private float mDotY;
    
    //--------------------------------------------------------------------
    //private float mPointerRadius = 2.0f;
    //private float mPointerX;
    //private float mPointerY;
    //private PointerView mPointerView;
    //--------------------------------------------------------------------
    
    public Dot(Context context) {
        super(context);
        //init();
    }
    
    public void setDotX(float x) {
        mDotX = x;
        invalidate();  // Huh ?
    }
    
    public void setDotX(float y) {
        mDotY = y;
        invalidate();  // Huh ?
    }
    
    public float getDotX(){
        return mDotX;
    }
    public float getDotY(){
        return mDotY;
    }
    
    public void setDotRadius(float r){
        mDotRadius = r;
        invalidate();  // Huh ?
    }
    public float getDotRadius(){
        return mDotRadius;   
    }
    public void setDotPaint(Paint p){
        mDotPaint = p;
        invalidate();  // Huh ?
    }
    public Paint getDotPaint(){
        return mDotPaint;
    }
    
    
    //--------------------------------------------------------------------
        /**
     * Returns the radius of the filled circle that is drawn at the tip of the current-item
     * pointer.
     *
     * @return The radius of the pointer tip, in pixels.
     */
    //public float getPointerRadius() {
    //    return mPointerRadius;
    //}

    /**
     * Set the radius of the filled circle that is drawn at the tip of the current-item
     * pointer.
     *
     * @param pointerRadius The radius of the pointer tip, in pixels.
     */
    //public void setPointerRadius(float pointerRadius) {
    //    mPointerRadius = pointerRadius;
    //    invalidate();
    //}
    //--------------------------------------------------------------------------
    
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // Do nothing. Do not call the superclass method--that would start a layout pass
        // on this view's children. PieChart lays out its children in onSizeChanged().
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.drawCircle(mDotX, mDotY, mDotRadius, mDotPaint);
        
        /*
        // Draw the shadow
        canvas.drawOval(mShadowBounds, mShadowPaint);

        // Draw the label text
        if (getShowText()) {
            canvas.drawText(mData.get(mCurrentItem).mLabel, mTextX, mTextY, mTextPaint);
        }

        // If the API level is less than 11, we can't rely on the view animation system to
        // do the scrolling animation. Need to tick it here and call postInvalidate() until the scrolling is done.
        if (Build.VERSION.SDK_INT < 11) {
            tickScrollAnimation();
            if (!mScroller.isFinished()) {
                postInvalidate();
            }
        }
        */
    } // end onDraw
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();

        int w = Math.max(minw, MeasureSpec.getSize(widthMeasureSpec));

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int minh = w + getPaddingBottom() + getPaddingTop();
        int h = Math.min(MeasureSpec.getSize(heightMeasureSpec), minh);

        setMeasuredDimension(w, h);
    }
    
    //public void onSizeChanged(){
    //    mPointerView.layout(0, 0, w, h);   
    //}
    private void init() {
        // Force the background to software rendering because otherwise the Blur
        // filter won't work.
        setLayerToSW(this);
        // The pointer doesn't need hardware acceleration, but in order to show up
        // in front of the pie it also needs to be on a separate view.
        mPointerView = new PointerView(getContext());
        addView(mPointerView);
        
    }
    private void setLayerToSW(View v) {
        if (!v.isInEditMode() && Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    private void setLayerToHW(View v) {
        if (!v.isInEditMode() && Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }
    
    /**
     * View that draws the pointer on top of the pie chart
     */
    private class PointerView extends View {

        /**
         * Construct a PointerView object
         *
         * @param context
         */
        public PointerView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawLine(mTextX, mPointerY, mPointerX, mPointerY, mTextPaint);
            canvas.drawCircle(mPointerX, mPointerY, mPointerRadius, mTextPaint);
        }
    }
    
    
    
    
}// end Dot Class
