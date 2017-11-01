package com.ljt.moduleinterface;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



/**
 * 宿主视图Fragment
 */
public class MasterFragment extends DialogFragment implements AdapterView.OnItemClickListener {

public static String TAG= MasterFragment.class.getSimpleName();
   public interface  OnItemSelectedListener{
        void onDataItemSelected(DataFragment.DataItem selected);
   }
    //用来创建实例
   public static MasterFragment newInstance(){
       return new MasterFragment();
   }
    private ArrayAdapter<DataFragment.DataItem> mAdapter;
    private OnItemSelectedListener mItemSelectedListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mItemSelectedListener= (OnItemSelectedListener) activity;
        }catch (ClassCastException e){
            throw new IllegalArgumentException("Activity must implement OnItemSelectedListener");
        }
    }

    /*
    * 构建一个自定义适配器来显示数据模型中的name字段
    * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter=new ArrayAdapter<DataFragment.DataItem>(getActivity(),
                android.R.layout.simple_list_item_1){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row=convertView;
                if(row==null){
                    row=LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1,
                            parent,false);
                }
                DataFragment.DataItem item = getItem(position);
                TextView tv = (TextView) row.findViewById(android.R.id.text1);
                tv.setText(item.getmName());
                return row;
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListView list = new ListView(getActivity());
        list.setOnItemClickListener(this);
        list.setAdapter(mAdapter);
        return list;
    }

    /*
    * 可以直接访问要显示的对话框
    * */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Select a title");
        return dialog;
    }

    /*
    * 从DataFrament中得到最新的数据信息
    * */
    @Override
    public void onResume() {
        super.onResume();
        //获得最新数据列表
        DataFragment fragment= (DataFragment) getFragmentManager()
                .findFragmentByTag(DataFragment.TAG);
        if(fragment!=null){
            mAdapter.clear();
            for(DataFragment.DataItem item:fragment.getLatestData()){
                mAdapter.add(item);
            }
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //通知Activity
        mItemSelectedListener.onDataItemSelected(mAdapter.getItem(position));

        //如果对话框显示的，则隐藏它
        //在fragment嵌入到某个视图的情况下，这个方法返回false;
        boolean showsDialog = getShowsDialog();
         Log.d(TAG,TAG+" ----->>>showsDialog= "+showsDialog);
        if(showsDialog){
            dismiss();
        }
    }
}


















