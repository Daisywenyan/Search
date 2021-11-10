package com.test.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.test.myapplication.R;
import com.test.myapplication.bean.ChatModel;

import java.util.ArrayList;

public class ChatService {
    private DatabaseHelper dbHelper;

    public ChatService(Context context) {
        dbHelper = new DatabaseHelper(context);
    }


    public ArrayList<ChatModel> getMessageList() {
        ArrayList<ChatModel> mlist=new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from chat ";
        Cursor cursor = sdb.rawQuery(sql,new String[]{});
        while (cursor.moveToNext()) { //遍历结果集

            int id = cursor.getInt(cursor.getColumnIndex("id") );
            String message = cursor.getString(cursor.getColumnIndex("message") );
            String type = cursor.getString(cursor.getColumnIndex("type") );
            ChatModel model = new ChatModel();
            if (type.equals(ChatModel.CHAT_B)){
                model.setIcon(R.mipmap.ic_boy);
            }else {
                model.setIcon(R.mipmap.ic_girl);
            }
            model.setContent(message);
            model.setType(type);
            model.setId(id);
            mlist.add(model);

        }
        return mlist;

    }


    public    void add(ChatModel chatModel) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();

        String sql = "insert into chat(message,type) values(?,?)";
        Object[] obj = {chatModel.getContent(), chatModel.getType()};
        sdb.execSQL(sql, obj);

    }


    public    void delete (int  id) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();

        sdb.execSQL( "Delete from chat where id= ? " , new Object[ ]{id} );
        sdb.close();

    }


}


