package com.test.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.test.myapplication.bean.PhotoBean;
import com.test.myapplication.databinding.FragmentHomeBinding;
import com.test.myapplication.databinding.PhotoItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 *  歌曲recycleview适配器 实现Filterable接口 用户输入的搜索过滤
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>  {


    private final List<PhotoBean.PhotosDTO> photosDTOList;//记录完整列表数据
    private final Context context;


    /**
     *  适配器构造方法
     * @param items  音乐列表
     * @param fragment 当前fragment
     */
    public MyItemRecyclerViewAdapter(List<PhotoBean.PhotosDTO> items, Context context) {

        photosDTOList=items;
        this.context=context;

    }

    /**
     *
     * @param parent
     * @param viewType
     * @return 创建veewholder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(PhotoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    /**
     * 适配器显示数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

                Glide.with(this.context).load(photosDTOList.get(position).getSrc().getMedium()).into(holder.imageView);
                holder.textView.setText(photosDTOList.get(position).getPhotographer());
                holder.mCvItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        new AlertDialog.Builder(context)
                                .setTitle("tips")
                                .setMessage("delete or not?")
                                //点击边缘弹窗是否会消失
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        photosDTOList.remove(position);
                                        dialog.dismiss();
                                        notifyDataSetChanged();

                                    }
                                })


                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                        return true;
                    }
                });
//        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return photosDTOList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageView;
        public final CardView mCvItem;
        public final TextView textView;



        public ViewHolder( PhotoItemBinding binding) {
            super(binding.getRoot());
            imageView = binding.ivImg;

            mCvItem = binding.cvItem;
            textView=binding.tvPhotographer;

        }

    }
}