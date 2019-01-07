package com.example.administrator.seek;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class sichuang_result extends ListActivity implements Runnable{
    String TAG="seek";
    Handler handler;
    private int msgwhat=9;

    private ArrayList<HashMap<String,String>> listItems;
    private  List<HashMap<String,String>> list;
    private SimpleAdapter listItemAdaper;

    public static String school=null;
    public static String type=null;
    public static String year=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.list);
        Intent intent = getIntent();
        school=intent.getStringExtra("school_key");
        type=intent.getStringExtra("type_key");
        year=intent.getStringExtra("year_key");

        //开启子线程
        Thread t=new Thread(this);
        t.start();

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == msgwhat) {
                    list = (List<HashMap<String,String>>)msg.obj;
                    listItemAdaper = new SimpleAdapter(sichuang_result.this,list,R.layout.list,new String[] {"majority","max","ave","min","kind"},new int[] { R.id.majority,R.id.max,R.id.ave,R.id.min,R.id.kind} );
                    setListAdapter(listItemAdaper);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void run() {   //在子线程里给主线程返回消息
        Log.i(TAG,"运行子线程");
        Bundle bundle = new Bundle();
        List<HashMap<String,String>> scoreList=new ArrayList<HashMap<String,String>>();

        try {
            DBManager manager = new DBManager(sichuang_result.this);
            manager.openDataBase();
            SQLiteDatabase db = manager.getDb();
            Cursor c = db.rawQuery("select * from gaokao where school=? and type=? and year=?", new String[]{school, type, year});

            if (c.moveToNext()) {
                int majorIndex, maxIndex, averageIndex, minIndex;
                majorIndex = c.getColumnIndex("major");
                maxIndex = c.getColumnIndex("max");
                averageIndex = c.getColumnIndex("average");
                minIndex = c.getColumnIndex("min");

                do {
                    String major = c.getString(majorIndex);
                    String max = c.getString(maxIndex);
                    String average = c.getString(averageIndex);
                    String min = c.getString(minIndex);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("majority", major);
                    map.put("max", max);
                    map.put("ave", average);
                    map.put("min", min);
                    scoreList.add(map);
                } while (c.moveToNext());
            }
            manager.closeDataBase();
        }catch(Exception e){
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage();
        msg.what=msgwhat;
        msg.obj = scoreList;
        handler.sendMessage(msg);
    }

}
