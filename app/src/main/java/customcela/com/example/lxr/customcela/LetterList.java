package customcela.com.example.lxr.customcela;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lxr on 2018/2/3.
 */

public class LetterList extends View {
    //A.要绘制的内容
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };
    private Paint paint;
    private int height;
    private int cellwidth;
    private int singleHeight;
    private OnClicklistner onClicklistner;
    int choose = -1;

    public LetterList(Context context) {
        this(context, null);
    }

    public LetterList(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < LETTERS.length; i++) {
            float letter = paint.measureText(LETTERS[i]) * 0.5f;
            canvas.drawText(LETTERS[i], cellwidth - letter, singleHeight + letter + i * singleHeight, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        cellwidth = getMeasuredWidth() / 2;
        singleHeight = height / LETTERS.length;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        int oldChoose = choose;
        int c = (int) (y / height * LETTERS.length);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (c >=0 && c < LETTERS.length) {
                    onClicklistner.setOncicklistner(LETTERS[c]);
                }
                break;

        }
        return true;
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(23);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);
    }

    interface OnClicklistner {
        void setOncicklistner(String s);
    }

    public void setListner(OnClicklistner onClicklistner) {
        this.onClicklistner = onClicklistner;
    }

}
