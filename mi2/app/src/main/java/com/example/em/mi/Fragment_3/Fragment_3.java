package com.example.em.mi.Fragment_3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.em.mi.R;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EM on 2017/4/17.
 */

public class Fragment_3 extends Fragment implements View.OnClickListener{
    private static final int INITIALIZE = 0;

    private ImageView no_shop;

    private ListView mListView;// 列表

    private ListAdapter mListAdapter;// adapter

    private List<DataBean> mListData = new ArrayList<DataBean>();// 数据

    private boolean isBatchModel;// 是否可删除模式

    private RelativeLayout mBottonLayout;
    private CheckBox mCheckAll; // 全选 全不选

    private TextView mEdit; // 切换到删除模式

    private TextView mPriceAll; // 商品总价

    private TextView mSelectNum; // 选中数量

    private TextView mFavorite; // 移到收藏夹

    private TextView mDelete; // 删除 结算

    private int totalPrice = 0; // 商品总价
    /** 批量模式下，用来记录当前选中状态 */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_3, null);
        mBottonLayout = (RelativeLayout) view.findViewById(R.id.cart_rl_allprie_total);
        mCheckAll = (CheckBox) view.findViewById(R.id.check_box);
        mEdit = (TextView) view.findViewById(R.id.subtitle);
        mPriceAll = (TextView) view.findViewById(R.id.tv_cart_total);
        mSelectNum = (TextView) view.findViewById(R.id.tv_cart_select_num);
        mFavorite = (TextView) view.findViewById(R.id.tv_cart_move_favorite);
        mDelete = (TextView) view.findViewById(R.id.tv_cart_buy_or_del);
        mListView = (ListView) view.findViewById(R.id.listview);
      //  no_shop= (ImageView) view.findViewById(R.id.no_shop);
        mListView.setSelector(R.drawable.list_selector);

        initListener();
        loadData();
        return view;


    }
    private void doDelete(List<Integer> ids)
    {
        for (int i = 0; i < mListData.size(); i++)
        {
            long dataId = mListData.get(i).getId();
            for (int j = 0; j < ids.size(); j++)
            {
                int deleteId = ids.get(j);
                if (dataId == deleteId)
                {
                    mListData.remove(i);
                    i--;
                    ids.remove(j);
                    j--;
                }
            }
        }

        refreshListView();
        mSelectState.clear();
        totalPrice = 0;
        mSelectNum.setText("已选" + 0 + "件商品");
        mPriceAll.setText("￥" + 0.00 + "");
        mCheckAll.setChecked(false);

    }
    private final List<Integer> getSelectedIds()
    {
        ArrayList<Integer> selectedIds = new ArrayList<Integer>();
        for (int index = 0; index < mSelectState.size(); index++)
        {
            if (mSelectState.valueAt(index))
            {
                selectedIds.add(mSelectState.keyAt(index));
            }
        }
        return selectedIds;
    }
    private void initListener(){
        mEdit.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mCheckAll.setOnClickListener(this);

    }

    private void loadData()
    {
        new LoadDataTask().execute(new Params(INITIALIZE));
    }

    private void refreshListView()
    {
        if (mListAdapter == null)
        {
            mListAdapter = new ListAdapter();
            mListView.setAdapter(mListAdapter);
            mListView.setOnItemClickListener(mListAdapter);

        } else
        {
            mListAdapter.notifyDataSetChanged();

        }
    }

    private List<DataBean> getData()
    {
        int maxId = 0;
        if (mListData != null && mListData.size() > 0)
            maxId = mListData.get(mListData.size() - 1).getId();
        List<DataBean> result = new ArrayList<DataBean>();
        DataBean data = null;
        for (int i = 0; i < 20; i++)
        {
            data = new DataBean();
            data.setId(maxId + i + 1);// 从最大Id的下一个开始
            data.setShopName("我的" + (maxId + 1 + i) + "店铺");
            data.setContent("我的购物车里面的第" + (maxId + 1 + i) + "个商品");
            data.setCarNum(1);
            data.setPrice(305f);
            result.add(data);
        }
        return result;
    }

    class Params
    {
        int op;

        public Params(int op)
        {
            this.op = op;
        }

    }

    class Result
    {
        int op;
        List<DataBean> list;
    }

    private class LoadDataTask extends AsyncTask<Params, Void, Result>
    {
        @Override
        protected Result doInBackground(Params... params)
        {
            Params p = params[0];
            Result result = new Result();
            result.op = p.op;
            try
            {// 模拟耗时
                Thread.sleep(500L);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            result.list = getData();
            return result;
        }

        @Override
        protected void onPostExecute(Result result)//添加方法
        {
            super.onPostExecute(result);
            if (result.op == INITIALIZE)
            {
                mListData = result.list;
            } else
            {
                mListData.addAll(result.list);
                Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show();
            }

            refreshListView();
        }

    }

    private class ListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener
    {

        @Override
        public int getCount()
        {
            return mListData.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent)//显示购物车列表
        {
            ViewHolder holder = null;
            View view = convertView;
            if (view == null)
            {

                view = LayoutInflater.from(getActivity()).inflate(R.layout.cart_list_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);

            } else
            {
                holder = (ViewHolder) view.getTag();
            }

            DataBean data = mListData.get(position);
            bindListItem(holder, data);
            holder.add.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    // TODO Auto-generated method stub

                    int _id = (int) mListData.get(position).getId();

                    boolean selected = mSelectState.get(_id, false);

                    mListData.get(position).setCarNum(mListData.get(position).getCarNum() + 1);

                    notifyDataSetChanged();

                    if (selected)
                    {
                        totalPrice += mListData.get(position).getPrice();
                        mPriceAll.setText("￥" + totalPrice + "");

                    }

                }
            });

            holder.red.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {

                    // TODO Auto-generated method stub
                    if (mListData.get(position).getCarNum() == 1)
                        return;

                    int _id = (int) mListData.get(position).getId();

                    boolean selected = mSelectState.get(_id, false);
                    mListData.get(position).setCarNum(mListData.get(position).getCarNum() - 1);
                    notifyDataSetChanged();

                    if (selected)
                    {
                        totalPrice -= mListData.get(position).getPrice();
                        mPriceAll.setText("￥" + totalPrice + "");

                    }

                }
            });
            return view;
        }

        private void bindListItem(ViewHolder holder, DataBean data)
        {

            holder.shopName.setText(data.getShopName());
            holder.content.setText(data.getContent());
            holder.price.setText("￥" + data.getPrice());
            holder.carNum.setText(data.carNum + "");
            int _id = data.getId();

            boolean selected = mSelectState.get(_id, false);
            holder.checkBox.setChecked(selected);

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            DataBean bean = mListData.get(position);

            ViewHolder holder = (ViewHolder) view.getTag();
            int _id = (int) bean.getId();

            boolean selected = !mSelectState.get(_id, false);
            holder.checkBox.toggle();
            if (selected)
            {
                mSelectState.put(_id, true);
                totalPrice += bean.getCarNum() * bean.getPrice();
            } else
            {
                mSelectState.delete(_id);
                totalPrice -= bean.getCarNum() * bean.getPrice();
            }
            mSelectNum.setText("已选" + mSelectState.size() + "件商品");
            mPriceAll.setText("￥" + totalPrice + "");
            if (mSelectState.size() == mListData.size())
            {
                mCheckAll.setChecked(true);
            } else
            {
                mCheckAll.setChecked(false);
            }

        }

    }

    class ViewHolder {
        CheckBox checkBox;

        ImageView image;
        TextView shopName;
        TextView content;
        TextView carNum;
        TextView price;
        TextView add;
        TextView red;

        public ViewHolder(View view) {
            checkBox = (CheckBox) view.findViewById(R.id.check_box);
            shopName = (TextView) view.findViewById(R.id.tv_source_name);
            image = (ImageView) view.findViewById(R.id.iv_adapter_list_pic);
            content = (TextView) view.findViewById(R.id.tv_intro);
            carNum = (TextView) view.findViewById(R.id.tv_num);
            price = (TextView) view.findViewById(R.id.tv_price);
            add = (TextView) view.findViewById(R.id.tv_add);
            red = (TextView) view.findViewById(R.id.tv_reduce);

        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.subtitle:
                isBatchModel = !isBatchModel;
                if (isBatchModel) {
                    mEdit.setText(getResources().getString(R.string.menu_enter));
                    mDelete.setText(getResources().getString(R.string.menu_del));
                 //   mBottonLayout.setVisibility(View.GONE);
                    mFavorite.setVisibility(View.VISIBLE);

                } else {
                    mEdit.setText(getResources().getString(R.string.menu_edit));

                    mFavorite.setVisibility(View.GONE);
              //      mBottonLayout.setVisibility(View.VISIBLE);
                    mDelete.setText(getResources().getString(R.string.menu_sett));
                }

                break;

/*000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000*/

            case R.id.check_box:
                if (mCheckAll.isChecked()) {

                    totalPrice = 0;
                    if (mListData != null) {
                        mSelectState.clear();
                        int size = mListData.size();
                        if (size == 0) {
                            return;
                        }
                        for (int i = 0; i < size; i++) {
                            int _id = (int) mListData.get(i).getId();
                            mSelectState.put(_id, true);
                            totalPrice += mListData.get(i).getCarNum() * mListData.get(i).getPrice();
                        }
                        refreshListView();
                        mPriceAll.setText("￥" + totalPrice + "");
                        mSelectNum.setText("已选" + mSelectState.size() + "件商品");

                    }
                } else {
                    if (mListAdapter != null) {
                        totalPrice = 0;
                        mSelectState.clear();
                        refreshListView();
                        mPriceAll.setText("￥" + 0.00 + "");
                        mSelectNum.setText("已选" + 0 + "件商品");

                    }
                }
                break;

            case R.id.tv_cart_buy_or_del:
                if (isBatchModel) {
                    List<Integer> ids = getSelectedIds();
                    doDelete(ids);
                } else {
                    Toast.makeText(getContext(), "结算", Toast.LENGTH_SHORT).show();
                }

                break;

            default:
                break;
        }
    }


}
