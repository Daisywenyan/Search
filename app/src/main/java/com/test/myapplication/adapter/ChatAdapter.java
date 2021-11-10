package com.test.myapplication.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.test.myapplication.R;
import com.test.myapplication.bean.ChatModel;
import com.test.myapplication.db.ChatService;

import java.util.ArrayList;

/**
 * Created by WangChang on 2016/4/28.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.BaseAdapter> {

    private final Context context;
    private ArrayList<ChatModel> dataList = new ArrayList<>();

    public ChatAdapter(Context context) {
        this.context=context;
    }


    public void addAll(ArrayList<ChatModel> list) {
        if (dataList != null && list != null) {
            dataList.clear();
            dataList.addAll(list);
            notifyItemRangeChanged(dataList.size(),list.size());
        }

    }

    @Override
    public BaseAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ChatAViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_a, parent, false));
            case 2:
                return new ChatBViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_b, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseAdapter holder, int position) {
        holder.setData(dataList.get(position),position);
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getType().equals(ChatModel.CHAT_A)){
            return 1;
        }else {
            return 2;
        }

    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class BaseAdapter extends RecyclerView.ViewHolder {

        public BaseAdapter(View itemView) {
            super(itemView);
        }

        void setData(Object object,int pos) {

        }
    }

    private class ChatAViewHolder extends BaseAdapter {
        private ImageView ic_user;
        private TextView tv;
        private LinearLayout rvItem;
        public ChatAViewHolder(View view) {
            super(view);
            ic_user = (ImageView) itemView.findViewById(R.id.ic_user);
            tv = (TextView) itemView.findViewById(R.id.tv);
            rvItem=itemView.findViewById(R.id.rv_item);
        }

        @Override
        void setData(Object object,int pos) {
            super.setData(object,pos);
            ChatModel model = (ChatModel) object;
            Glide.with(itemView.getContext()).load(model.getIcon()).placeholder(R.mipmap.ic_launcher).into(ic_user);
            tv.setText(model.getContent());
            rvItem.setOnLongClickListener(new View.OnLongClickListener() {
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
                                    ChatService chatService=new ChatService(context);
                                    chatService.delete(model.getId());
                                    dataList.remove(pos);
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
        }
    }

    private class ChatBViewHolder extends BaseAdapter {
        private ImageView ic_user;
        private TextView tv;
        private RelativeLayout rvItem;

        public ChatBViewHolder(View view) {
            super(view);
            ic_user = (ImageView) itemView.findViewById(R.id.ic_user);
            tv = (TextView) itemView.findViewById(R.id.tv);
            rvItem=itemView.findViewById(R.id.rv_item);

        }


        @Override
        void setData(Object object,int pos) {
            super.setData(object,pos);
            ChatModel model = (ChatModel) object;
            Glide.with(itemView.getContext()).load(model.getIcon()).placeholder(R.mipmap.ic_launcher).into(ic_user);
            tv.setText(model.getContent());
            rvItem.setOnLongClickListener(new View.OnLongClickListener() {
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
                                    ChatService chatService=new ChatService(context);
                                    chatService.delete(model.getId());
                                    dataList.remove(pos);
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
        }
    }
}
