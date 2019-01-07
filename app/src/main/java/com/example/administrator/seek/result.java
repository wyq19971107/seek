package com.example.administrator.seek;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.NoCopySpan;
import android.util.Log;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/11/11.
 */

public class result extends ListActivity implements Runnable{

    String TAG="seek";
    Handler handler;
    private int msgwhat=9;

    private ArrayList<HashMap<String,String>> listItems;
    private  List<HashMap<String,String>> list;
    private SimpleAdapter listItemAdaper;

    public static String place=null;
    public static String type=null;
    public static String year=null;
    public static String school=null;

    public static String school_code=null;
    public static String place_code=null;
    public static String type_code=null;

    protected void onCreate(Bundle savedInstanceState) {
        //   setContentView(R.layout.list);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        place=intent.getStringExtra("place_key");
        type=intent.getStringExtra("type_key");
        year=intent.getStringExtra("year_key");
        school=intent.getStringExtra("school_key");

        //获取学校对应代码
        DBManager manager = new DBManager(result.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor c1 = db.rawQuery("select * from school where school=? ", new String[]{school});
        if (c1.moveToNext()) {
            int school_code_Index;
            school_code_Index = c1.getColumnIndex("code");
            school_code = c1.getString(school_code_Index);
        }

        //获取省份对应代码
        Cursor c2 = db.rawQuery("select * from shengfen where shengfen=? ", new String[]{place});
        if (c2.moveToNext()) {
            int place_code_Index;
            place_code_Index = c2.getColumnIndex("code");
            place_code = c2.getString(place_code_Index);
        }

        manager.closeDataBase();

        if(type.equals("文科"))
            type_code="10034";
        else if(type.equals("理科"))
            type_code="10035";

        //开启子线程
        Thread t=new Thread(this);
        t.start();

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == msgwhat) {
                    list = (List<HashMap<String,String>>)msg.obj;
                   listItemAdaper = new SimpleAdapter(result.this,list,R.layout.list,new String[] {"majority","max","ave","min","kind"},new int[] { R.id.majority,R.id.max,R.id.ave,R.id.min,R.id.kind} );
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
        //获取网络数据
        Document doc = null;
        try {
            String url = "https://gkcx.eol.cn/schoolhtm/specialty/"+school_code+"/"+type_code+"/specialtyScoreDetail_"+year+"_"+place_code+".htm";
            doc = Jsoup.connect(url).get();
            Log.i(TAG, "run: " + doc.title());

            Elements table = doc.getElementsByTag("table");
            Elements trs = table.select("tr");
            for (int i = 1; i < trs.size(); i++) {
                Elements tds = trs.get(i).select("td");
                Element td0 = tds.get(0);
                Element td1 = tds.get(1);
                Element td2 = tds.get(2);
                Element td3 = tds.get(3);
                Element td4 = tds.get(4);
                Element td5 = tds.get(5);

                String str0 = td0.text();
                String str1 = td1.text();
                String str2 = td2.text();
                String str3 = td3.text();
                String str4 = td4.text();
                String str5 = td5.text();
                // Log.i(TAG,str0+" "+str1+" "+str2+" "+str3+" "+str4+" "+str5);

                HashMap<String,String> map=new HashMap<String, String>();
                map.put("majority",str0);
               // map.put("year",str1);
                map.put("max",str2);
                map.put("ave",str3);
                map.put("min",str4);
                map.put("kind",str5);
                scoreList.add(map);

            }
        }catch (MalformedURLException e) {
            Log.e("www",e.toString());
            e.printStackTrace();
        }catch (IOException e){
            Log.e("www",e.toString());
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage();
        msg.what=msgwhat;
        msg.obj = scoreList;
        handler.sendMessage(msg);
    }
}
