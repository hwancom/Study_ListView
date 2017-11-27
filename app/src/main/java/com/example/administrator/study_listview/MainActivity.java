package com.example.administrator.study_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter; // (35)

    // (38)
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // (39)
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        // listView를 찾는다. (1)
        ListView listView = findViewById(R.id.listView);

        // ListView에다 Adapter 객체를 만든다. (30)
        adapter = new SingerAdapter();

        /* 'addItem()'에 'new SingerItem'이라고 생성자를 통해 객체를 만들어서
        *  파라미터로 전달해서 data를 추가한다. (32) */
        adapter.addItem(new SingerItem("소녀시대", "010-1000-10000", R.drawable.icon_1));
        adapter.addItem(new SingerItem("걸스데이", "010-2000-20000", R.drawable.icon_2));
        adapter.addItem(new SingerItem("여자친구", "010-3000-30000", R.drawable.icon_3));
        adapter.addItem(new SingerItem("티아라", "010-4000-40000", R.drawable.icon_4));
        adapter.addItem(new SingerItem("애프터스쿨", "010-5000-50000", R.drawable.icon_5));

        // 'setAdapter()'에 adapter 객체를 파라미터로 전달한다. (31)
        listView.setAdapter(adapter);

        // Item을 클릭했을때 처리하기 위한 Listener 정의. (33)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // adapter 객체를 참조하기 위해 SingerAdapter를 변수로 선언한다. (34)

                // (36)
                SingerItem item = (SingerItem) adapter.getItem(i);

                // Toast를 띄운다. (37)
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        // Button 이벤트 처리 (40)
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 입력상자의 값을 가져온다. (41)
                String name = editText1.getText().toString();
                String mobile = editText2.getText().toString();

                // adapter에 'addItem()'으로 추가한다. (42)
                adapter.addItem(new SingerItem(name, mobile, R.drawable.icon_1));

                // ListView를 갱신한다. (43)
                adapter.notifyDataSetChanged();
            }
        });
    }

    /* listView에다가 설정할 Adapter를 객체로 만들기 위한 Adapter class를 정의한다.
    *  기존에 제공되는 Adapter를 상속해서 필요한 만큼 code를 넣을 수 있게 한다.
    *  일반적으로 BaseAdapter를 상속한다. (2) */
    class SingerAdapter extends BaseAdapter {
        // data를 관리할 ArrayList를 만든다. (4)
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();
        /* SingerItem 객체를 ArrayList에 넣어주면
        *  SingerAdapter에 SingerItem 객체가 여러개 들어갈 수 있게 된다. (11) */

        // 외부에서 data를 추가할 수 있도록 method를 정의한다. (29)
        public void addItem(SingerItem item) {
            items.add(item);
        }

        /* 나타내려는 data가 글자 하나가 아니기 때문에
        *  각 정보를 담을 수 있는 별도의 객체를 정의한다.
        *  SingerItem.java를 만든다. (5) */

        // 4개의 method를 implement 한다. (3)
        @Override
        public int getCount() {
            return items.size();
        }
        /* 'ListView'가 보여지는 시점에 설정된 'Adapter'를 통해 나타낼 data의 갯수를 묻는다.
        *  이때 items.size()를 리턴해 주어서
        *  'ArrayList<SingerItem> items = new ArrayList<SingerItem>()'의
        *  갯수를 알려준다. (12) */

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }
        // items.get(i)를 리턴해서 position을 알려준다. 여기에서 i는 index 값이다. (13)

        @Override
        public long getItemId(int i) {
            return i;
        }
        // Item에 ID값이 있다면 넘겨달라는 것. 여기서는 그냥 'i'를 넘겨준다. (14)

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            /*

            // 'SingerItemView'를 변수 'singerItemView'에 할당한다. (26)
            SingerItemView singerItemView = new SingerItemView(getApplicationContext());

            */

            // View를 재사용하는 코드 작성 (44)
            SingerItemView singerItemView = null;
            if (view == null) {
                singerItemView = new SingerItemView(getApplicationContext());
            } else {
                singerItemView = (SingerItemView) view;
            }

            // 몇번째 singerItemView 인지 position 값인 'i'를 설정해 준다. (27)
            SingerItem item = items.get(i);
            singerItemView.setName(item.getName());
            singerItemView.setMobile(item.getMobile());
            singerItemView.setImage(item.getResId());

            // 'singerItemView'를 리턴해 준다. (28)
            return singerItemView;
        }
        /* getView는 화면에 나타나는 각각의 View도 Adapter에서 만들어 달라는 의미.
        *  View는 layout으로 구성된다. 그러므로 layout에 해당하는 부분화면을 정의하고
        *  그것을 이용해 객체를 만든 다음에 data를 설정하고 return을 해준다.
        *  부분화면 정의를 위해 'singer_item.xml'을 만든다. (15) */
    }
}
