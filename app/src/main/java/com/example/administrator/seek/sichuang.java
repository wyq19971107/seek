package com.example.administrator.seek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Administrator on 2019/1/4.
 */

public class sichuang extends Activity implements AdapterView.OnItemSelectedListener{
    private static Spinner school_spinner=null;
    private static Spinner type_spinner=null;
    private static Spinner year_spinner=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sichuang);

        //获取界面布局文件中的spinner组件
        school_spinner=(Spinner)findViewById(R.id.Spinner01);
        type_spinner=(Spinner)findViewById(R.id.Spinner02);
        year_spinner=(Spinner)findViewById(R.id.Spinner03);

        //为Spinner设置选中事件监听器
        school_spinner.setOnItemSelectedListener(this);
        type_spinner.setOnItemSelectedListener(this);
        year_spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
        String content=parent.getItemAtPosition(position).toString();
        switch(parent.getId()){
            case R.id.Spinner01:
                Toast.makeText(sichuang.this,"选择的学校是："+content,Toast.LENGTH_SHORT).show();
                break;
            case R.id.Spinner02:
                Toast.makeText(sichuang.this,"选择的类别是："+content,Toast.LENGTH_SHORT).show();
                break;
            case R.id.Spinner03:
                Toast.makeText(sichuang.this,"选择的年份是："+content,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public void chaxun(View view){
        String school=school_spinner.getSelectedItem().toString();
        String type=type_spinner.getSelectedItem().toString();
        String year=year_spinner.getSelectedItem().toString();

        Intent sichuang_result=new Intent(this,sichuang_result.class);
        sichuang_result.putExtra("school_key",school);
        sichuang_result.putExtra("type_key",type);
        sichuang_result.putExtra("year_key",year);
        startActivityForResult(sichuang_result,1);

    }


}
