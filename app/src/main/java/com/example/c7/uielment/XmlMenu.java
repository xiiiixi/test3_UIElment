package com.example.c7.uielment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by c7 on 2019/3/29.
 */

public class XmlMenu extends AppCompatActivity{
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_menu);
        txt= (TextView) findViewById(R.id.content_txt);
    }

    public  boolean onCreateOptionsMenu(Menu menu){//开发选项菜单重写的方法
        MenuInflater inflater=new MenuInflater(this);//菜单动态加载类
        inflater.inflate(R.menu.xmlmenu,menu);//调用inflate方法解析菜单文件,装填R.Menu.xmlmenu菜单，并添加到menu中
        super.onCreateOptionsMenu(menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.font_10:
                txt.setTextSize(20);
                break;
            case R.id.font_16:
                txt.setTextSize(32);
                break;
            case R.id.font_20:
                txt.setTextSize(40);
                break;
            case R.id.red_font:
                txt.setTextColor(Color.RED);
                break;
            case R.id.black_font:
                txt.setTextColor(Color.BLACK);
                break;
            case R.id.plain_item:
                Toast toast = Toast.makeText(XmlMenu.this,"这是普通单击项",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
        }
    }

