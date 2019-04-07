package com.example.c7.uielment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    Intent a, b, c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button)findViewById(R.id.listview);
        button1.setOnClickListener(new ButtonListener());
        button2=(Button)findViewById(R.id.dialog);
        button2.setOnClickListener(new ButtonListener());
        button3=(Button)findViewById(R.id.xmlmenu);
        button3.setOnClickListener(new ButtonListener());
        button3=(Button)findViewById(R.id.actionmodeMenu);
        button3.setOnClickListener(new ButtonListener());
    }

    //使用switch、case实现监听作用
    //setClass函数的第一个参数是一个Context对象
//Context是一个类,Activity是Context类的子类,也就是说,所有的Activity对象都可以向上转型为Context对象
//setClass函数的第二个参数是Class对象,在当前场景下,应该传入需要被启动的Activity的class对象
    //只跳转一个活动时：可以使用如下几行代码
    //Intent intent=new Intent();
    //intent.setClass(MainActivity.this, SecondActivity.class);
    //startActivity(intent);
    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.listview:
                    a=new Intent(MainActivity.this,ListViewDemo.class);
                    startActivity(a);
                    break;
                case R.id.dialog:
                    b=new Intent(MainActivity.this,AlertDialogDemo.class);
                    startActivity(b);
                    break;
                case R.id.xmlmenu:
                    c=new Intent(MainActivity.this,XmlMenu.class);
                    startActivity(c);
                    break;
                case R.id.actionmodeMenu:
                    d=new Intent(MainActivity.this,ActionModeMenuDemo.class);
                    startActivity(d);
                    break;
                default:
                    break;
            }
        }
    }
}
