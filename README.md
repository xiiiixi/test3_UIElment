# test3_UIElment
这是实验3-UI组件
# 实验三
学号：123012016072 
姓名：陈琪
班级：软工2班

## 一、实验内容
ui组件：
(1):利用SimpleAdapter实现界面效果
(2):创建自定义布局的AlertDialog
(3):使用XML定义菜单
(4):创建上下文操作模式(ActionMode)的上下文菜单
## 二、实验步骤和结果
### (1)关键代码：
**1、ListViewDemo.java:**
```
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

```
**2、AlertDialogDemo.java:**
```
package com.example.c7.uielment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by c7 on 2019/3/28.
 */

public class AlertDialogDemo extends AppCompatActivity {
    private Button bt_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        initView();
        bt_show.setOnClickListener(new View.OnClickListener(){
            View myView = LayoutInflater.from(AlertDialogDemo.this).inflate(R.layout.alertdialog_acticity, null);
            ImageView head=(ImageView) myView.findViewById(R.id.header);
            EditText user = (EditText) myView.findViewById(R.id.name);
            EditText password = (EditText) myView.findViewById(R.id.code);

           @Override
           public void onClick(View v){
               AlertDialog.Builder builder=new AlertDialog.Builder(AlertDialogDemo.this);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                      Toast.makeText(AlertDialogDemo.this,"Cancel",Toast.LENGTH_SHORT);
                   }
               });
               builder.setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
               @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                   Toast.makeText(AlertDialogDemo.this,user.getText().toString(),Toast.LENGTH_SHORT);
                   }
            });
                AlertDialog dialog=builder.create();
                dialog.setView(myView);
                dialog.show();
            }
        });
    }

    private void initView() {
        bt_show = (Button) findViewById(R.id.bt_show);
    }
}
```

**3、XmlMenu.java:**
```
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
```

**4、ActionModeMenuDemo .java:**
```
package com.example.c7.uielment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


//actionBarActivity已经由AppCompatActivity取代了
public class ActionModeMenuDemo extends AppCompatActivity{
    ListView listView;
    List<String> datas = new ArrayList<String>();
    ListViewAdapter adapter;
    ModeCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listView = (ListView)findViewById(R.id.list);
        initData();
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        callback = new ModeCallback();
        listView.setMultiChoiceModeListener(callback);
    }



    //定义一个实现MultiChoiceModeListener接口的类
    class ModeCallback implements AbsListView.MultiChoiceModeListener {
        View actionBarView;
        TextView selectedNum;

        public void updateSelectedCount() {
            int selectedCount = listView.getCheckedItemCount();
            selectedNum.setText(selectedCount + "");
        }

        //列表项的选中状态被改变时调用
        @Override
        public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
            updateSelectedCount();
            mode.invalidate();
            adapter.notifyDataSetChanged();
        }


        //进入多选模式调用，初始化ActionBar的菜单和布局
        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.multiple_mode_menu, menu);
            if(actionBarView == null) {
                actionBarView = LayoutInflater.from(ActionModeMenuDemo.this).inflate(R.layout.actionbar_view, null);
                selectedNum = (TextView)actionBarView.findViewById(R.id.selected_num);
            }
            mode.setCustomView(actionBarView);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }


        //ActionBar上的菜单项被点击时调用
        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            switch(item.getItemId()) {
                case R.id.select_all:
                    for(int i = 0; i < adapter.getCount(); i++) {
                        listView.setItemChecked(i, true);
                    }
                    updateSelectedCount();
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.unselect_all:
                    listView.clearChoices();
                    updateSelectedCount();
                    adapter.notifyDataSetChanged();
                    break;
            }
            return true;
        }

        //退出多选模式时调用
        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {
            listView.clearChoices();
        }
    }




    //自定义Adapter类
    class ListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder viewHolder;
            if(convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(ActionModeMenuDemo.this, R.layout.item, null);
                viewHolder.text = (TextView)convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.text.setText(datas.get(position));
            if(listView.isItemChecked(position)) {
                convertView.setBackgroundColor(Color.RED);
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }
            return convertView;
        }

        class ViewHolder {
            TextView text;
        }
    }

    public void initData() {
      //  datas.add("one");
     //   datas.add("two");
      //  datas.add("three");
      //  datas.add("four");
      //  datas.add("five");
       for(int i = 1; i <= 15; i++) {
           datas.add(i + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.enter_mode) {
            listView.setItemChecked(0, true);
            listView.clearChoices();
            callback.updateSelectedCount();
        }
        return super.onOptionsItemSelected(item);
    }
}
```

**5、xmlmenu.xml:**
```
<?xml version="1.0" encoding="utf-8"?>
<!--根元素，无任何属性-->
<menu xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <!--定义菜单项-->
    <item
        android:title="@string/font_size">
    <!--子菜单-->
    <menu>
        <!--定义一组单选菜单项-->
        <group>
            <!--定义多个菜单项-->
            <item
                android:id="@+id/font_10"
                android:title="@string/font10"/>
            <item
                android:id="@+id/font_16"
                android:title="@string/font16"/>
            <item
                android:id="@+id/font_20"
                android:title="@string/font20"/>
        </group>
    </menu>
    </item>

    <!--定义一个普通菜单项-->


    <!--定义一组普通菜单项-->
    <item
        android:title="@string/font_color">
    <menu>
        <group>
            <!--定义两个菜单项-->
            <item
                android:id="@+id/red_font"
                android:title="@string/red_title" />
            <item
                android:title="@string/black_title"
                android:id="@+id/black_font"/>
        </group>
    </menu>
    </item>
    <item
        android:id="@+id/plain_item"
        android:title="@string/plain_item" />
</menu>
```

### (2)结果截图：
#### 文件目录截图:
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190407144224653.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)

#### 1、主界面（主活动）：按下按钮跳转相应布局：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401194752413.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
#### 2、利用SimpleAdapter实现的界面效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401194957967.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
#### 3、创建自定义布局的AlertDialog：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401195024144.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401195039571.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
#### 4、使用XML定义菜单：
##### （1）XML定义菜单
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401195130922.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
##### （2）改变字体大小
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401195342313.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
##### （3）改变字体颜色
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401195352190.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
##### （4）显示普通菜单项
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401195402432.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)

#### 5、创建上下文操作模式(ActionMode)的上下文菜单：
第四题没有成功：
以下是错误截图，不知道错在哪里，编译没错，运行出错，页面跳不过，是利用listview的CHOICE_MODE_MULTIPLE_MODAL多选模式，已经尝试寻找解决方法，但是没解决。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190407150307537.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190407150319669.jpg)

## 三、总结
   第四题：可以通过点击菜单项和长按列表项进入多选模式，进入多选模式后，ActionBar的布局和菜单项会发生改变，可以在ActionBar上显示选中的条目数
   ListView多选操作模式详解：
   https://blog.csdn.net/qq_17766199/article/details/49511869
   https://blog.csdn.net/zh175578809/article/details/72802461
