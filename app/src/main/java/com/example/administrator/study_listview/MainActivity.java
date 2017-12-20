package com.example.administrator.study_listview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView = null;

    ListViewAdapter adapter;

    EditText editText_Filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adapter 생성
        adapter = new ListViewAdapter();

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_36dp), "김하나", "010-1000-1000");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_36dp), "박두리", "010-2000-2000");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_assignment_ind_black_36dp), "이세시", "010-3000-3000");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_box_black_36dp), "최똘이", "010-4000-4000");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_36dp), "남다서", "010-5000-5000");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_assignment_ind_black_36dp), "육오이", "010-6000-6000") ;

        editText_Filter = findViewById(R.id.editText_Filter);
        editText_Filter.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String filterText = editable.toString();

                /**
                 * Filtering 텍스트 팝업은 ListView가 표시하는 UI이며, ListView의 setFilterText()
                 * 함수를 통해 filtering text가 전달되면 무조건 표시되게 만들어져 있다.
                 *
                 * 텍스트 팝업을 보이지 않게 처리하려면
                 * ListView를 통하지 않고 Adapter로 부터 직접 Filter 객체의 참조를 가져와서
                 * filter() 함수를 호출하면 된다.*/

                /*
                if (filterText.length() > 0) {
                    listView.setFilterText(filterText);
                } else {
                    listView.clearTextFilter();
                }
                */

                ((ListViewAdapter) listView.getAdapter()).getFilter().filter(filterText);
            }
        });
    }

}
