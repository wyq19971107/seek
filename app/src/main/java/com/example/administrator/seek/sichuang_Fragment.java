package com.example.administrator.seek;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


 public class sichuang_Fragment extends Fragment
 {
     public static sichuang_Fragment newInstance(String param1)
     {
         sichuang_Fragment fragment = new sichuang_Fragment();
         Bundle args = new Bundle();
         args.putString("agrs1", param1);
         fragment.setArguments(args);
         return fragment;
     }
     public sichuang_Fragment() { }
     public void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
     }
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
     {
         View view = inflater.inflate(R.layout.sichuang, container, false);
         return view;
     }
 }
