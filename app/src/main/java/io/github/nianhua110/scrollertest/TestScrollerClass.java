package io.github.nianhua110.scrollertest;

import android.content.Context;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by kankan on 2016/5/15.
 */
public class TestScrollerClass extends LinearLayout {
    ScrollerCompat scrollerCompat;
    Button button;
    public TestScrollerClass(Context context) {
        super(context);
        init(context);
    }

    public TestScrollerClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    void init(Context context){
        button = new Button(context);
        button.setText("cd");
        addView(button);
        //第一步，创造对象
        scrollerCompat = ScrollerCompat.create(context);
        //scrollerCompat.startScroll(0,0, 10,10, 1000);
        //postInvalidate();
    }
    public void startScroller(){

            Log.d("sd", "start scroller");
            //第三步，启动滑动
            scrollerCompat.startScroll(0, 100, 500, 8, 1000);
            postInvalidate();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        button.layout(button.getLeft(), button.getTop(), button.getRight(), button.getBottom());
        Log.i("sd", "number is "+ button.getLeft());

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //第二步，响应computerScroll函数，一定要在计算完位置后调用，通过computeScrollOffset函数的返回值判断
        if(scrollerCompat.computeScrollOffset()) {
            Log.i("sd", "computeScroll" + scrollerCompat.getCurrX());
            button.layout(scrollerCompat.getCurrX(), button.getTop(), scrollerCompat.getCurrX() + button.getMeasuredWidth(), button.getBottom());
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("sd", "on Measure");
    }
}
