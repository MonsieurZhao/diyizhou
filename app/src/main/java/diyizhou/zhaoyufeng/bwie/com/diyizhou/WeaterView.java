package diyizhou.zhaoyufeng.bwie.com.diyizhou;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WeaterView extends View {
    private Paint mPaintTop,mPainBottom;
    private Path mPathTop,mPathBottom;
    private float φ;
    private TypedArray typedArray;
    private int color;

    public WeaterView(Context context) {
        super(context);
        init(context);
    }

    public WeaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeaterView);
        color = typedArray.getColor(R.styleable.WeaterView_back_Color, Color.RED);
        typedArray.recycle();
        init(context);
    }

    public WeaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        mPathTop = new Path();
        mPathBottom = new Path();

        mPaintTop = new Paint();
        mPaintTop.setColor(color);
        mPaintTop.setAntiAlias(true);
        mPainBottom = new Paint();

        mPainBottom.setColor(color);
        mPainBottom.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathTop.reset();
        mPathBottom.reset();
        mPathTop.moveTo(getLeft(),getBottom());
        mPathBottom.moveTo(getLeft(),getTop());

        double mY = Math.PI*2/getWidth();
        φ-=-0.1f;

        for(float x=0;x<=getWidth();x+=20){
            float y = (float) ((10*Math.sin(mY*x+φ))+10);
            float y2 = (float) ((10*Math.cos(mY*x+φ)));
            mPathTop.lineTo(x,y);
            mPathBottom.moveTo(x,y2);

        }

        mPathTop.lineTo(getRight(),getTop());
        mPathBottom.lineTo(getRight(),getBottom());

        canvas.drawPath(mPathTop,mPaintTop);
        canvas.drawPath(mPathBottom,mPainBottom);
        postInvalidateDelayed(20);
    }
}
