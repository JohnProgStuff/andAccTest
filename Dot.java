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
    
}
