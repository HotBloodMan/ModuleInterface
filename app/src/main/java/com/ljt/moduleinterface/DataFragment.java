package com.ljt.moduleinterface;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * 封装应用程序的数据逻辑
 */
public class DataFragment extends Fragment {

    public static String TAG= DataFragment.class.getSimpleName();

    public static class DataItem{
        private String mName;
        private String mUrl;

        public DataItem(String mName, String mUrl) {
            this.mName = mName;
            this.mUrl = mUrl;
        }

        public String getmName() {
            return mName;
        }

        public String getmUrl() {
            return mUrl;
        }

    }
    //用来创建新的实例
    public static DataFragment newInstance(){
        return new DataFragment();
    }

    private ArrayList<DataItem>  mDataSet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //构建初始化数据
        mDataSet = new ArrayList<DataItem>();
        mDataSet.add(new DataItem("Baidu", "https://www.baidu.com"));
        mDataSet.add(new DataItem("CSDN", "http://www.csdn.net"));
        mDataSet.add(new DataItem("Android", "http://www.android.com"));
    }
        public ArrayList<DataItem> getLatestData(){
            return mDataSet;
        }


}
