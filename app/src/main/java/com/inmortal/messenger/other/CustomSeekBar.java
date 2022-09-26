package com.inmortal.messenger.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class CustomSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {

    private Rect rect;
    private Paint paint ;
    private int seekbar_height;

    public CustomSeekBar(Context context) {
        super(context);

    }

    public CustomSeekBar(Context context, AttributeSet attrs) {

        super(context, attrs);
        rect = new Rect();
        paint = new Paint();
        seekbar_height = 6;
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        rect.set(getThumbOffset(),
                (getHeight() / 2) - (seekbar_height/2),
                getWidth()- getThumbOffset(),
                (getHeight() / 2) + (seekbar_height/2));

        paint.setColor(Color.LTGRAY);

        canvas.drawRect(rect, paint);



        if (this.getProgress() > 0) {


            rect.set(getWidth() / 2,
                    (getHeight() / 2) - (seekbar_height/2),
                    getWidth() / 2 + (getWidth() / 200) * (getProgress() - 0),
                    getHeight() / 2 + (seekbar_height/2));

            paint.setColor(Color.BLACK);
            canvas.drawRect(rect, paint);
        }

        if (this.getProgress() < 0) {

            rect.set(getWidth() / 2 - ((getWidth() / 200) * (0 - getProgress())),
                    (getHeight() / 2) - (seekbar_height/2),
                    getWidth() / 2,
                    getHeight() / 2 + (seekbar_height/2));

            paint.setColor(Color.BLACK);
            canvas.drawRect(rect, paint);
        }
       super.onDraw(canvas);
    }
}
