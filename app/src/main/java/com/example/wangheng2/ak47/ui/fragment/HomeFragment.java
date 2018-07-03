package com.example.wangheng2.ak47.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangheng2.ak47.R;
import com.example.wangheng2.ak47.contract.HomeContract;
import com.example.wangheng2.ak47.entity.GoodsCategory;
import com.example.wangheng2.ak47.entity.StoreTransaction;
import com.example.wangheng2.ak47.entity.StoreTransactionSum;
import com.example.wangheng2.ak47.entity.Transaction;
import com.example.wangheng2.ak47.okhttp.digest.Credentials;
import com.example.wangheng2.ak47.presenter.HomePresenter;
import com.example.wangheng2.ak47.ui.fragment.base.BaseFragment;
import com.example.wangheng2.ak47.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.RequestBody;

/**
 * Created by wangheng2 on 2017/11/13.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    @Inject
    HomePresenter homePresenter;

    private View rootView;
    private Context context;
    private TextView tv;
    @BindView(R.id.home_gridview)
    RecyclerView gv;
    @BindView(R.id.home_listview)
    RecyclerView lv;

    @BindView(R.id.home_scrollview)
    NestedScrollView sv;
    private List<GoodsCategory> list = new ArrayList<GoodsCategory>();
    private List<StoreTransaction> listDatas = new ArrayList<StoreTransaction>();

    @BindView(R.id.home_appbar)
    AppBarLayout appbar;
    private LinearLayoutManager llm;
    private GridLayoutManager glm;
    @BindView(R.id.home_toolbar)
    Toolbar tb;
    @BindView(R.id.home_appbar_re_layout)
    RelativeLayout rl;
    @BindView(R.id.home_toolbar_rl)
    RelativeLayout home_toolbar_rl;
    private TextView searchText;
    @BindView(R.id.chartshow_wb)
    WebView chartshow_wb;

    HomeGridAdapter homeGridAdapter;
    HomeListAdapter homeListAdapter;

    private boolean webviewInited = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_layout, container, false);
        ButterKnife.bind(this, rootView);
        initView();
//        getData();
        bindData();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println("onCreateView");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        System.out.println("onAttach");
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        System.out.println("onActiveityCreaated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        homePresenter.onResume();
    }

    private void initView() {
        glm = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        llm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        chartshow_wb.getSettings().setAllowFileAccess(true);
        //开启脚本支持
        chartshow_wb.getSettings().setJavaScriptEnabled(true);
        chartshow_wb.loadUrl("file:///android_asset/html/test.html");
        //在js中调用本地java方法
        chartshow_wb.addJavascriptInterface(this, "AndroidWebView");

        //添加客户端支持
        chartshow_wb.setWebChromeClient(new WebChromeClient());
    }

    @JavascriptInterface
    public void setWebViewReady() {
        System.out.println("zoule");
        webviewInited = true;
        homePresenter.listStoreRanking();
    }

    private void bindData() {
        gv.setLayoutManager(glm);
        homeGridAdapter = new HomeGridAdapter();
        homeListAdapter = new HomeListAdapter();
        gv.setAdapter(homeGridAdapter);
        gv.setNestedScrollingEnabled(false);
        lv.setLayoutManager(llm);
        lv.setAdapter(homeListAdapter);
//        sv.setOnScrollChangeListener(new MyScrollListener());
        lv.setNestedScrollingEnabled(false);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            float alpha = 0;
            int count = 0;
            float scale = 0;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int height_appbar = appBarLayout.getMeasuredHeight();
                int height_rl = rl.getMeasuredHeight();

                //改变透明度
                verticalOffset = 0 - verticalOffset;
                int alphaStartHeight = (height_appbar - height_rl) / 2 + 60;
                if (verticalOffset <= alphaStartHeight) {
                    alpha = 0;
                    home_toolbar_rl.setAlpha(alpha);
                    home_toolbar_rl.setVisibility(View.INVISIBLE);
                } else if (verticalOffset < height_appbar - 90 && verticalOffset > alphaStartHeight) {
                    home_toolbar_rl.setVisibility(View.VISIBLE);
                    scale = (float) (verticalOffset - alphaStartHeight) / (height_appbar - alphaStartHeight - height_rl);
                    alpha = (float) (1 * scale);
                    // 随着滑动距离改变透明度
                    home_toolbar_rl.setAlpha(alpha);
                } else {
                    home_toolbar_rl.setVisibility(View.VISIBLE);
                    if (alpha < 1) {
                        // 防止频繁重复设置相同的值影响性能
                        alpha = 1;
                        home_toolbar_rl.setAlpha(alpha);
                    }
                }
            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams) params).setMargins(15, 15, 15, 15);
        listView.setLayoutParams(params);

    }

    @Override
    public void showGoodCategories(List<GoodsCategory> goodsCategories) {
        list.clear();
        list.addAll(goodsCategories);
        homeGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLastTenTransactions(List<StoreTransaction> storeTransactions) {
        listDatas.clear();
        listDatas.addAll(storeTransactions);
        System.out.println("whzhanshile");
        homeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showStoreRanking(List<StoreTransactionSum> storeTransactionSums) {
        System.out.println("showStoreRanking:" + storeTransactionSums);
        Gson gson = new Gson();
        String msg = gson.toJson(storeTransactionSums);
        chartshow_wb.loadUrl("javascript:showStoreRanking(" + msg + ")");
    }

    @Override
    public boolean isWebviewInitialized(){
        return webviewInited;
    }

    @Override
    public void showNetWorkError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void shideLoadingDialog() {

    }

    class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.MyGridViewHolder> {
        AdapterView.OnItemClickListener listener;

        @Override
        public MyGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyGridViewHolder holder = new MyGridViewHolder(LayoutInflater.from(context).inflate(R.layout.home_gridview_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyGridViewHolder holder, int position) {
            GoodsCategory goodsCategory = list.get(position);
            Picasso.with(context).load(goodsCategory.getAvatar()).into(holder.iv);
            holder.tv.setText(goodsCategory.getName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.image_item)
            ImageView iv;
            @BindView(R.id.text_item)
            TextView tv;

            public MyGridViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
//                if (getAdapterPosition() == 0) {
//                    Object[] params = new Object[4];
//                    params[0] = HttpConstants.LOGIN_URL;
//                    params[1] = "POST";
//                    params[2] = null;
//                    params[3] = new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            System.out.println("登录失败");
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            System.out.println(response.body().string());
////                            Toast.makeText(context, response.body().string(), Toast.LENGTH_SHORT).show();
//                        }
//                    };
//                    new HttpTask().execute(params);
//                }
//                if (getAdapterPosition() == 1) {
//                    Object[] params = new Object[4];
//                    params[0] = HttpConstants.GET_ORGNIZATION_URL;
//                    params[1] = "GET";
//                    params[2] = null;
//                    params[3] = new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            System.out.println("查询机构列表失败");
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            System.out.println(response.body().string());
////                            Toast.makeText(context, response.body().string(), Toast.LENGTH_SHORT).show();
//                        }
//                    };
//                    new HttpTask().execute(params);
//                }
            }
        }
    }

    @Override
    public void setInitialSavedState(@Nullable SavedState state) {
        super.setInitialSavedState(state);
    }

//    private class HttpTask extends AsyncTask<Object, Object, Void> {
//
//        @Override
//        protected Void doInBackground(Object... strings) {
//            try {
//                Credentials credentials = new Credentials("admin", "admin");
//                HttpUtil.setCredentials(credentials);
//                HttpUtil.request((String) strings[0], (String) strings[1], (RequestBody) strings[2], (Callback) strings[3]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void s) {
//        protected void onPostExecute(Void s) {
//            super.onPostExecute(s);
//        }
//    }

    class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.MyListViewHolder> {

        @Override
        public MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyListViewHolder holder = new MyListViewHolder(LayoutInflater.from(context).inflate(R.layout.home_listview_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyListViewHolder holder, int position) {
            StoreTransaction storeTransaction = listDatas.get(position);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String, String>>>() {
            }.getType();
            List<Map<String, String>> pics = gson.fromJson(storeTransaction.getItem_picList(), type);
            String picUrl0 = pics.get(0).get("pic0");
            Picasso.with(context).load(picUrl0).into(holder.iv);
            holder.title.setText(storeTransaction.getStoreName());
            holder.time.setText(storeTransaction.getRequestTime());

            holder.transType.setText("0".equals(storeTransaction.getTransactionType()) ? "买入" : "卖出");
            holder.goodType.setText(transGoodsTypeToStringCN(storeTransaction.getItem_typeId()));
        }

        @Override
        public int getItemCount() {
            return listDatas == null ? 0 : listDatas.size();
        }

        class MyListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.home_listvew_item_imageView)
            ImageView iv;
            @BindView(R.id.home_listvew_item_left_panel_store_title)
            TextView title;
            @BindView(R.id.home_listvew_item_left_panel_time)
            TextView time;
            @BindView(R.id.home_listvew_item_left_panel_transaction_type)
            TextView transType;
            @BindView(R.id.home_listvew_item_left_panel_good_type)
            TextView goodType;

            public MyListViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(context, title.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String transGoodsTypeToStringCN(String type) {
        String typeString = null;
        switch (type) {
            case "1":
                typeString = "手机";
                break;
            case "2":
                typeString = "电动车";
                break;
            case "3":
                typeString = "金银首饰";
                break;
            case "4":
                typeString = "电脑";
                break;
            case "5":
                typeString = "有色金属";
                break;
            case "6":
                typeString = "其他";
                break;
        }
        return typeString;
    }

//    class MyScrollListener implements NestedScrollView.OnScrollChangeListener {
//        // 将透明度声明成局部变量用于判断
//        int alpha = 0;
//        int count = 0;
//        float scale = 0;
//
//        @Override
//        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//            int height = appbar.getMeasuredHeight();
//            System.out.println("height:" + height);
//            System.out.println("scrollY:" + scrollY);
//            if (scrollY <= height / 2) {
//                System.out.println("小与height/2");
//                alpha = 0;
////                tb.setBackgroundColor(Color.argb(alpha, 100, 100, 252));
//                home_toolbar_rl.setAlpha(alpha);
//            } else if (scrollY <= height && scrollY > height / 2) {
//                System.out.println("中间");
//                scale = (float) (scrollY - height / 2) / (height / 2);
//                alpha = (int) (1 * scale);
//                System.out.println("scale：" + scale);
//                System.out.println("alpha：" + alpha);
//                // 随着滑动距离改变透明度
//                // Log.e("al=","="+alpha);
////                tb.setBackgroundColor(Color.argb(alpha, 100, 100, 252));
//                home_toolbar_rl.setAlpha(alpha);
//            } else {
//                System.out.println("else");
//                if (alpha < 1) {
//                    // 防止频繁重复设置相同的值影响性能
//                    alpha = 1;
//                    System.out.println("scale：" + scale);
//                    System.out.println("alpha：" + alpha);
////                    tb.setBackgroundColor(Color.argb(alpha, 100, 100, 252));
//                    home_toolbar_rl.setAlpha(alpha);
//                }
//            }
//        }
//    }


}
