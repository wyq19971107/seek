package com.example.administrator.seek;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;



public class personApplication extends Application    //用于连接数据库
{
    public void onCreate()
   {
       super.onCreate();
       AVOSCloud.initialize(this,"MLwDLFcc9us9lalsrXv536wn-gzGzoHsz","s68PLGnIvLd7qIWfdg99pcNB");
   }
}
