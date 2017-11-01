package com.ljt.moduleinterface;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private WebView mWebView;

    /*
    *自定义web客户端启用进度显示。
    * */
    private WebViewClient mWebViewClient=new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            getActivity().setProgressBarVisibility(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            getActivity().setProgressBarIndeterminateVisibility(false);
        }
    };

    //设置一个显示的WebView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        mWebView=new WebView(getActivity());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(mWebViewClient);
        return mWebView;
    }
    //外部方法，用来将一个新的网站内容加载到视图
    public void loadUrl(String url){
       mWebView.loadUrl(url);
    }

}
