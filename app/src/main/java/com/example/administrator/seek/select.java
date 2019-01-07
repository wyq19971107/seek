package com.example.administrator.seek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Administrator on 2019/1/6.
 */

public class select extends Activity implements AdapterView.OnItemSelectedListener{
    private static Spinner place_spinner=null;
    private static Spinner type_spinner=null;
    private static Spinner year_spinner=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        //获取界面布局文件中的spinner组件
        place_spinner=(Spinner)findViewById(R.id.Spinner_select_01);
        type_spinner=(Spinner)findViewById(R.id.Spinner_select_02);
        year_spinner=(Spinner)findViewById(R.id.Spinner_select_03);

        //为Spinner设置选中事件监听器
        place_spinner.setOnItemSelectedListener(this);
        type_spinner.setOnItemSelectedListener(this);
        year_spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
        String content=parent.getItemAtPosition(position).toString();
        switch(parent.getId()){
            case R.id.Spinner_select_01:
                Toast.makeText(select.this,"选择的地区是："+content,Toast.LENGTH_SHORT).show();
                break;
            case R.id.Spinner_select_02:
                Toast.makeText(select.this,"选择的类别是："+content,Toast.LENGTH_SHORT).show();
                break;
            case R.id.Spinner_select_03:
                Toast.makeText(select.this,"选择的年份是："+content,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public void chaxun_select(View view){
        String place=place_spinner.getSelectedItem().toString();
        String type=type_spinner.getSelectedItem().toString();
        String year=year_spinner.getSelectedItem().toString();
        EditText school=(EditText)findViewById(R.id.editText_select);
        String school_select=school.getText().toString();

        Intent result=new Intent(this,result.class);
        result.putExtra("place_key",place);
        result.putExtra("type_key",type);
        result.putExtra("year_key",year);
        result.putExtra("school_key",school_select);
        startActivityForResult(result,1);

    }


}
