package com.ideotekno.framework.mvvm.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import com.ideotekno.framework.mvvm.R;
import com.ideotekno.framework.mvvm.databinding.ItemBlogEmptyViewBinding;
import com.ideotekno.framework.mvvm.ui.base.BaseViewHolder;
import com.ideotekno.framework.mvvm.ui.feed.blogs.BlogEmptyItemViewModel;
import com.ideotekno.framework.mvvm.utils.NetworkUtils;
import com.ideotekno.framework.mvvm.utils.ViewUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class GenericAdapter<T, D> extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOAD = 2;
    private Context mContext;
    private ArrayList<T> mArrayList;

    public abstract void onBindData(T model, int position, D dataBinding);

    public abstract int getLayoutResId();

    public abstract void onItemClick(T model, int position);

    public abstract void onRetry();

    public abstract void noConnection();

    public abstract String setTitle();

    public GenericAdapter(Context context, ArrayList<T> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(mContext.getApplicationContext());
    }

    @Override
    public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutResId(), parent, false);
                return new ItemViewHolder(dataBinding);
            case VIEW_TYPE_EMPTY:
                ItemBlogEmptyViewBinding blogViewBinding1 = ItemBlogEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(blogViewBinding1);
            default:
                ViewDataBinding dataBindings = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutResId(), parent, false);
                return new ItemViewHolder(dataBindings);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mArrayList != null && !mArrayList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else if (mArrayList.isEmpty() && isNetworkConnected()) {
            return VIEW_TYPE_EMPTY;
        } else if (!isNetworkConnected() || mArrayList == null) {
            noConnection();
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_LOAD;
        }
    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        holder.onBind(position);
        holder.itemView.setOnClickListener(view -> {
            if (!mArrayList.isEmpty()) {
                onItemClick(mArrayList.get(position), position);
            }
        });
    }

    public void clearItems() {
        mArrayList.clear();
    }

    @Override
    public int getItemCount() {
        if (mArrayList != null && mArrayList.size() > 0) {
            return mArrayList.size();
        } else {
            return 1;
        }
    }

    public void addItems(ArrayList<T> arrayList) {
        mArrayList = arrayList;
        this.notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mArrayList.get(position);
    }

    public class EmptyViewHolder extends BaseViewHolder implements BlogEmptyItemViewModel.BlogEmptyItemViewModelListener {
        private ItemBlogEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemBlogEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mArrayList.isEmpty() && isNetworkConnected()) {
                mBinding.imageViewEmpty.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_baseline_update));
                mBinding.tvMessage.setText(ViewUtils.setValuesOf(mContext, R.string.info_data_not_found, setTitle(), ""));
            } else if (!isNetworkConnected()) {
                mBinding.imageViewEmpty.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_cloud_off));
                mBinding.tvMessage.setText(mContext.getString(R.string.info_no_internet));
            }
            BlogEmptyItemViewModel emptyItemViewModel = new BlogEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            GenericAdapter.this.onRetry();
        }
    }

    class ItemViewHolder extends BaseViewHolder {
        protected D mDataBinding;

        public ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mDataBinding = (D) binding;
        }

        @Override
        public void onBind(int position) {
            onBindData(mArrayList.get(position), position, mDataBinding);
        }
    }
}