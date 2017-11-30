package com.example.administrator.study_listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {
    // textView를 변수로 선언 (23)
    TextView textView1;
    TextView textView2;
    ImageView imageView;

    // 'Alt+Insert로 2개의 필수 생성자를 만든다. (17)
    public SingerItemView(Context context) {
        super(context);

        // 각각의 생성자에서 init()을 호출하도록 한다. (19)
        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // 각각의 생성자에서 init()을 호출하도록 한다. (20)
        init(context);
    }

    //초기화를 위한 'init'이라는 method를 정의하고 (18)
    private void init(Context context) {

        /* 'singer_itemview.xml.xml'을 inflation 해서 이곳에 붙여주는 역활을 하도록 지정한다.
        *  inflation을 위해서 LAYOUT_INFLATER_SERVICE 라고 하는 상수를 넣어서
        *  시스템 서비스를 참조할 수 있다. (21) */
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_itemview, this, true);

        /* 이렇게 inflation 하고 나면 xml layout 안에 있는 imageView textView 등을
        *  findViewById로 참조할 수 있다. (22) */
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
    }

    // data를 설정해 줄 method를 정의해 준다. (24)
    public void setName(String name) {
        textView1.setText(name);
    }

    public void setMobile(String mobile) {
        textView2.setText(mobile);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

    // 'MainActivity'의 getView()에서 coding 한다. (25)
}
