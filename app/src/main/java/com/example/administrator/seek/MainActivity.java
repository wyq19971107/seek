package com.example.administrator.seek;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.BACKGROUND_STYLE_RIPPLE;
import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_SHIFTING;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    String TAG="main";

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
     private sichuang_Fragment my_sichuang_Fragment;
     private all_Fragment my_all_Fragment;
    /* private FavoritesFragment mFavoritesFragment;
     private BookFragment mBookFragment; */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);


        bottomNavigationBar.setMode(MODE_SHIFTING) // 设置mode
                .setBackgroundStyle(BACKGROUND_STYLE_RIPPLE)  // 背景样式
                .setBarBackgroundColor("#2FA8E1") // 背景颜色
                .setInActiveColor("#929292") // 未选中状态颜色
                .setActiveColor("#ffffff") // 选中状态颜色
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Movies & TV"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Games"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();


        DBManager manager = new DBManager(MainActivity.this);
        manager.openDataBase();
        SQLiteDatabase db = manager.getDb();
        Cursor cursor = db.query("gaokao", null, null, null, null, null, null);
        if (cursor.moveToNext()) {
            int nameIndex, ageIndex, sexIndex;
            nameIndex = cursor.getColumnIndex("year");

            do {
                String name = cursor.getString(nameIndex);
                //使用Log查看数据,未在界面展示
                Log.i(TAG,"name:"+name);
            }while(cursor.moveToNext());
        }
        manager.closeDataBase();
    }

    /** * 设置默认的 */
    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        my_sichuang_Fragment = sichuang_Fragment.newInstance("位置");
        transaction.replace(R.id.tb, my_sichuang_Fragment);
        transaction.commit();
    }

    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (my_sichuang_Fragment == null) {
                     my_sichuang_Fragment = sichuang_Fragment.newInstance("位置");
                }
                transaction.replace(R.id.tb, my_sichuang_Fragment);
                break;
            case 1:
                if (my_all_Fragment == null) {
                    my_all_Fragment = all_Fragment.newInstance("发现");
                }
                transaction.replace(R.id.tb,my_all_Fragment);
                break;
            case 2:
              /*  if (mFavoritesFragment == null) {
                    mFavoritesFragment = FavoritesFragment.newInstance("爱好");
                }
                transaction.replace(R.id.tb, mFavoritesFragment);*/
                break;
            case 3:
               /* if (mBookFragment == null) {
                    mBookFragment = BookFragment.newInstance("图书");
                }
                transaction.replace(R.id.tb, mBookFragment);*/
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }
    public void onTabReselected(int position) { }


    public void seek(View view) {  //按钮响应事件
        Intent select = new Intent(this,select.class);
        startActivity(select);
    }

    public void sichuang(View view) {
        Intent sichuang = new Intent(this,sichuang.class);
        startActivity(sichuang);
    }
}
