package com.ljt.moduleinterface;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class MainActivity extends FragmentActivity implements MasterFragment.OnItemSelectedListener {

    public static String TAG= MainActivity.class.getSimpleName();
    private MasterFragment mMaster;
    private DetailFragment mDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        setProgressBarIndeterminateVisibility(false);

        DataFragment fragment = (DataFragment) getSupportFragmentManager()
                .findFragmentByTag(DataFragment.TAG);
        if(fragment==null){
            fragment=DataFragment.newInstance();
            //保存这个实例
            fragment.setRetainInstance(true);
            FragmentTransaction ft=
                    getSupportFragmentManager().beginTransaction();
            ft.add(fragment,DataFragment.TAG);
            ft.commit();
        }
        mDetail= (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_detail);
        mDetail.setRetainInstance(true);
        Log.d(TAG,TAG+" ----->>> mDetail="+mDetail);
        mMaster=MasterFragment.newInstance();
        View container=findViewById(R.id.fragment_master);
        if(container !=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_master,mMaster);
            ft.commit();
        }
    }

    @Override
    public void onDataItemSelected(DataFragment.DataItem selected) {
        String s = selected.getmUrl();
         Log.d(TAG,TAG+" ----->>> s="+s.toString());
        mDetail.loadUrl(selected.getmUrl());
    }
    //Button点击事件
    public void onShowClick(View v){
        mMaster.show(getSupportFragmentManager(),null);
    }
}
