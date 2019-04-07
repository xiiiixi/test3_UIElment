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



