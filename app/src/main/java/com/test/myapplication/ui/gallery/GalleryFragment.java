package com.test.myapplication.ui.gallery;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.test.myapplication.R;
import com.test.myapplication.adapter.ChatAdapter;
import com.test.myapplication.bean.ChatModel;
import com.test.myapplication.databinding.FragmentGalleryBinding;
import com.test.myapplication.db.ChatService;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private ChatAdapter adapter;
    private String content;
    ArrayList<ChatModel> data = new ArrayList<>();
    private ChatService chatService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        setHasOptionsMenu(true);


        binding.textGallery.setHasFixedSize(true);
        binding.textGallery.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.textGallery.setAdapter(adapter = new ChatAdapter(getActivity()));
        initData();
        binding.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.rb_recipient){
                    binding.rbRecipient.setChecked(true);
                    binding.rbSender.setChecked(false);
                }else {
                    binding.rbRecipient.setChecked(false);
                    binding.rbSender.setChecked(true);

                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
    private void initData() {
         chatService=new ChatService(getActivity());
        ArrayList<ChatModel> messageList = chatService.getMessageList();
        data.clear();
        if (null!=messageList&&messageList.size()>0){
            data.addAll(messageList);
            adapter.addAll(data);
        }

        binding.et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content = s.toString().trim();
            }
        });

        binding.tvSend.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.et.getText().toString())) {
                    Snackbar.make(view, "Enter search keywords", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    ChatModel model = new ChatModel();
                    if (binding.rbRecipient.isChecked()){

                        model.setIcon(R.mipmap.ic_girl);
                        model.setContent(content);
                        model.setType(ChatModel.CHAT_A);
                        data.add(model);
                    }else if (binding.rbSender.isChecked()){

                        model.setIcon(R.mipmap.ic_boy);
                        model.setContent(content);
                        model.setType(ChatModel.CHAT_B);
                        data.add(model);
                    }
                    chatService.add(model);
                    ArrayList<ChatModel> messageList = chatService.getMessageList();
                    data.clear();
                    if (null!=messageList&&messageList.size()>0){
                        data.addAll(messageList);
                        adapter.addAll(data);
                    }
                    adapter.addAll(data);
                    binding.et.setText("");
                    hideKeyBorad(binding.et);

                }

            }
        });

    }

    private void hideKeyBorad(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);



    }
}