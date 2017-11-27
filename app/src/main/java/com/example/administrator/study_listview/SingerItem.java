package com.example.administrator.study_listview;

// 이 class 안에는 단순히 걸그룹에 대한 data를 담아 둘 것이다.
public class SingerItem {

    // 한 명의 걸그룹에 대한 data를 정의한다. (6)
    String name;
    String mobile;
    int resId;

    // 'Alt+Insert'로 각 변수에 대한 생성자를 만든다. (7)
    public SingerItem(String name, String mobile, int resId) {
        this.name = name;
        this.mobile = mobile;
        this.resId = resId;
    }

    /* data 변수에 직접 접근하지 않게하기 위해서
    *  'Alt+Insert'로 'Getter and Setter' method를 만든다. (8) */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    /* 객체 안에 들어있는 data를 문자열로 출력하기 위한 'toString()' method를
        *  'Alt+Insert'를 통해 만든다. (9) */
    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    /* 이렇게 만들어진 SingerItem을 MainActivity.java의 ArrayList에 집어넣는다. (10) */
}
