package com.example.c7.uielment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by c7 on 2019/3/27.
 */

public class ListViewDemo extends AppCompatActivity{
    //用于显示布局里的动物名称
    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","elephant"};
    private int[] image=new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listview_activity);
        final int color1=0xFFC5B5FF;
        final int color2=0xFFFFFFFF;
        //创建一个list集合，list集合的元素是Map
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for (int i=0;i<names.length;i++){
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("header",names[i]);
            listItem.put("images",image[i]);
            listItems.add(listItem);//加入list集合
        }

        //创建一个SimpleAdapter,此处严格按照定义数组names与image顺序,否则会出现程序build成功却运行失败且难以解决错误
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.simple_items,new String[]{"header","images"},new  int[]{R.id.header,R.id.images});
        final  ListView list=(ListView)findViewById(R.id.mylist);
        //为ListView设置Adapter
        list.setAdapter(simpleAdapter);
        //对应点击事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int flag=0;
                System.out.println(names[position]+position+"被单击");
                //点击则改变状态，改变颜色
                switch (flag){
                    case 0:
                        //此处对应上面布局文件的点击函数
                        view.setSelected(true);
                        CharSequence text=names[position];
                        //定义一个Toast表示哪一个图片所在item被点击
                        int duration= Toast.LENGTH_SHORT;
                        Toast toast=Toast .makeText(ListViewDemo.this,text,duration);
                        toast.show();
                        flag=1;
                        break;
                    case 1:
                        view.setSelected(false);
                        CharSequence text1=names[position];
                        int duration1= Toast.LENGTH_SHORT;
                        Toast toast1=Toast .makeText(ListViewDemo.this,text1,duration1);
                        toast1.show();
                        flag=0;
                        break;
                }
            }
        });
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                System.out.println(names[position]+"选中");
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }
}
