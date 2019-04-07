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



