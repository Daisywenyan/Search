package com.test.myapplication.ui.home;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.google.android.material.snackbar.Snackbar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.test.myapplication.R;
import com.test.myapplication.SharedPreferencesUtil;
import com.test.myapplication.adapter.MyItemRecyclerViewAdapter;
import com.test.myapplication.bean.PhotoBean;
import com.test.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ArrayList<PhotoBean.PhotosDTO> photoList=new ArrayList<>();
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.textHome.setText("");
        binding.textHome.setHint("Enter search keywords");
        SharedPreferencesUtil.getInstance(getActivity(),"inputData");
        String inputMsg = (String) SharedPreferencesUtil.getData("inputMsg", "");
        if (!TextUtils.isEmpty(inputMsg)){
            binding.textHome.setText(inputMsg);
        }
        binding.btSearch.setOnClickListener(view -> {
            if (TextUtils.isEmpty(binding.textHome.getText().toString())) {
                Snackbar.make(view, "Enter search keywords", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                SharedPreferencesUtil.getInstance(getActivity(),"inputData");
                SharedPreferencesUtil.putData("inputMsg",binding.textHome.getText().toString());
              getPhotoListData();
            }
        });


        binding.llRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //创建适配器
        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(photoList,getActivity());
        //设置适配器
        binding.llRecycleview.setAdapter(myItemRecyclerViewAdapter);
        //显示送i所mnue
//        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_settings){
            new AlertDialog.Builder(getActivity())
                    .setTitle("Instructions for use")
                    .setMessage("1. Enter the keyword you want to search on the homepage and click the button to request the network to find the desired picture and display it in the list\n" +
                            "2. Click on the chat page to send the talk. The content is stored in the local database and displayed in the list")
                    //点击边缘弹窗是否会消失
                    .setCancelable(true)
                    .show();
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);



    }

    /**
     * get photo list
     */
    private void getPhotoListData() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> {
            OkGo.<String>get(Urls.GET_PHOTO+binding.textHome.getText().toString())//
                    .tag(this)
                   .execute(new StringCallback() {
                       @Override
                       public void onSuccess(Response<String> response) {
                           if (response.code()==200){
                               Toast.makeText(getContext(),"Get information successfully",Toast.LENGTH_SHORT).show();
                               PhotoBean photoBean= JSON.parseObject(response.body(),PhotoBean.class);
                               List<PhotoBean.PhotosDTO> photos = photoBean.getPhotos();
                               photoList.addAll(photos);
                               myItemRecyclerViewAdapter.notifyDataSetChanged();

                           }

                       }

                       @Override
                       public void onError(Response<String> response) {
                           super.onError(response);
                       }
                   });




        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}