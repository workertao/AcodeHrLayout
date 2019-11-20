package com.acode.hr.layout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import androidx.core.view.ViewCompat;

public class HrLayout extends LinearLayout {
    public static final String TAG = "HRLAYOUT";

    private float currY = 0;
    //总共的偏移量，当为0时，说明没有偏移
    private float totalOffsetY = 0;

    //默认露出多少高度
    private float defaultHeight;

    //预计要露出多少高度
    private float realHeight;

    //拉伸距离顶部的间距
    private float realMarginTop;

    //收缩时候距离顶部的距离
    private float defaultMarginTop;

    //记录滑动的状态，上滑or下滑
    private float startY, moveState;

    public HrLayout(Context context) {
        this(context, null);
    }

    public HrLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HrLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HrLayout);
        if (typedArray != null) {
            defaultHeight = typedArray.getDimension(R.styleable.HrLayout_defaultHeight, 0);
            realHeight = typedArray.getDimension(R.styleable.HrLayout_realHeight, 0);
            typedArray.recycle();
        }
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //计算高度
        int height = MeasureSpec.getSize(heightMeasureSpec);
        totalOffsetY = height - defaultHeight;
        setY(totalOffsetY);
        //设置view收缩状态下距离顶部的间距
        defaultMarginTop = totalOffsetY;
        //设置view拉伸状态下距离顶部的间距
        realMarginTop = height - realHeight;
        Log.d(TAG, "测量高度：" + height + "   defaultHeight：" + defaultHeight + "   realHeight：" + realHeight);
    }

    //设置默认露出的高度
    public HrLayout setDefaultHeight(float defaultHeight) {
        this.defaultHeight = defaultHeight;
        return this;
    }

    //设置预计露出的高度
    public HrLayout setRealHeight(float realHeight) {
        this.realHeight = realHeight;
        return this;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "子view是否到达顶点");
                currY = ev.getRawY();
                startY = currY;
                Log.d(TAG, "totalOffsetY:" + totalOffsetY + "    realMarginTop:" + realMarginTop);
                //如果偏移量已经到达拉伸目标值，则交给子view消费
                if (totalOffsetY <= realMarginTop) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //如果偏移量已经到达拉伸目标值，则交给子view消费
                Log.d(TAG, "分发-移动");
                float moveY = ev.getRawY();
                moveState = moveY - startY;
                if (moveState > 0) {
                    //下滑
                    Log.d(TAG, "分发-下滑");
                    if (totalOffsetY <= realMarginTop && canChildScrollUp()) {
                        //外层的view到达顶点&&子view不在顶点，此处不拦截，交给子view
                        Log.d(TAG, "分发-下滑-不拦截");
                        return false;
                    }
                    Log.d(TAG, "分发-下滑-拦截");
                    return true;
                } else if (moveState < 0) {
                    //上滑
                    Log.d(TAG, "分发-上滑");
                    if (totalOffsetY <= realMarginTop) {
                        //只要偏移量到达预计值，就不拦截事件，直接分发到下级
                        return false;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent-ACTION_UP:");
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currY = event.getRawY();
                startY = currY;
                Log.d(TAG, "currY:" + currY);
                if (totalOffsetY <= realMarginTop) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getRawY();
                //计算偏移量，得到正负值，-上 +下
                moveState = moveY - startY;
                totalOffsetY = moveY - currY + totalOffsetY;
                if (totalOffsetY <= realMarginTop && moveState < 0) {
                    //偏移量到达预计值&&是上滑状态，直接breank
                    totalOffsetY = realMarginTop;
                    break;
                }
                currY = moveY;
                setTranslationY(totalOffsetY);
                Log.d(TAG, "moveY:" + moveY + "    currY:" + currY + "   偏移量：" + totalOffsetY + "  滑动状态：" + moveState);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "   getY:" + getY());
                currY = (int) event.getRawY();
                Log.d(TAG, "currY:" + currY);
                if (moveState > 0) {
                    //下滑
                    Log.d(TAG, "下滑");
                    startAnim(getY(), defaultMarginTop);
                } else if (moveState < 0) {
                    //上滑
                    Log.d(TAG, "上滑");
                    startAnim(getY(), realMarginTop);
                }
                break;
        }
        return true;
    }


    //判断子view是否滑动到顶部，这段代码是swiper里边的
    public boolean canChildScrollUp() {
        View childView = getChildAt(0);
        Log.d(TAG, "子viewid：" + childView.getId());
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (childView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) childView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || childView.getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(childView, -1) || childView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(childView, -1);
        }
    }

    //动画
    private void startAnim(float startHeight, final float endHeight) {
        //上滑
        ValueAnimator anim = ValueAnimator.ofFloat(startHeight, endHeight);
        anim.setDuration(200);
        // 设置动画运行的时长
        anim.setRepeatCount(0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                setTranslationY(currentValue);
                totalOffsetY = endHeight;
                Log.d(TAG, "动画：" + totalOffsetY);
            }
        });
        anim.start();
    }
}
