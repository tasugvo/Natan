package com.example.natan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {

    private int mXc,mYc; // coordenadas do centro do círculo

    private int mRadius; // raio do círculo
    private int mCircleColor; // cor do círculo
    private int mTextColor; // cor do texto
    private String mText;



    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);
        try {
            mXc=a.getInt(R.styleable.CircleView_XC,0);
            mYc=a.getInt(R.styleable.CircleView_YC,0);
            mRadius=a.getInt(R.styleable.CircleView_Radius,0);
            mCircleColor=a.getInt(R.styleable.CircleView_CircleColor,0);
            mTextColor=a.getInt(R.styleable.CircleView_TextColor,0);
            mText=a.getString(R.styleable.CircleView_Text);
        }
        finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;
        if(viewWidthHalf>viewHeightHalf)
            mRadius=viewHeightHalf-10;
        else
            mRadius=viewWidthHalf-10;
        mXc=viewWidthHalf;
        mYc=viewHeightHalf;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mCircleColor);
        canvas.drawCircle(mXc, mYc, mRadius, paint);
        paint.setColor(mTextColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        canvas.drawText(mText, mXc, mYc, paint);
    }


}


//package com.example.natan;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//
//public class CircleView extends View {
//
//    private int mXc, mYc; // coordenadas do centro do círculo
//    private int mRadius; // raio do círculo
//    private int mCircleColor; // cor do círculo
//    private int mTextColor; // cor do texto
//    private String mText; // texto exibido no círculo
//
//    public CircleView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//
//        // Obter atributos personalizados
//        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);
//        try {
//            mXc = a.getInt(R.styleable.CircleView_XC, 0);
//            mYc = a.getInt(R.styleable.CircleView_YC, 0);
//            mRadius = a.getInt(R.styleable.CircleView_Radius, 0);
//            mCircleColor = a.getColor(R.styleable.CircleView_CircleColor, 0);
//            mTextColor = a.getColor(R.styleable.CircleView_TextColor, 0);
//            mText = a.getString(R.styleable.CircleView_Text);
//            if (mText == null) {
//                mText = ""; // Evita problemas de texto nulo
//            }
//        } finally {
//            a.recycle();
//        }
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        Paint paint = new Paint();
//        int viewWidthHalf = this.getMeasuredWidth() / 2;
//        int viewHeightHalf = this.getMeasuredHeight() / 2;
//
//        if (viewWidthHalf > viewHeightHalf) {
//            mRadius = viewHeightHalf - 10;
//        } else {
//            mRadius = viewWidthHalf - 10;
//        }
//
//        mXc = viewWidthHalf;
//        mYc = viewHeightHalf;
//
//        // Desenhar o círculo
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(mCircleColor);
//        canvas.drawCircle(mXc, mYc, mRadius, paint);
//
//        // Desenhar o texto
//        paint.setColor(mTextColor);
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(50);
//        canvas.drawText(mText, mXc, mYc, paint);
//    }
//}
