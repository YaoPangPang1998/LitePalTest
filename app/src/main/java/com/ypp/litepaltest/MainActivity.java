package com.ypp.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "sssssss";
    private Button create_data;
    private Button add_data;
    private Button updata_data;
    private Button delete_data;
    private Button query_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_data=findViewById(R.id.create_database);
        add_data=findViewById(R.id.add_data);
        updata_data=findViewById(R.id.updata_data);
        delete_data=findViewById(R.id.delete_data);
        query_data=findViewById(R.id.query_data);
        create_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //没有该数据库则创建
                 LitePal.getDatabase();
            }
        });
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPress("Unknow");
                book.setPrice(16.96);
                book.save();
            }
        });
        updata_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name=? and author=?","The Lost Symbol","Dan Brown");
//                book.setName("The Lost Symbol");
//                book.setAuthor("Dan Brown");
//                book.setPages(510);
//                book.setPrice(19.95);
//                book.setPress("Unknow");
//                book.save();
//                book.setPrice(10.99);
//                book.save();
            }
        });
        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price<?","15");            }
        });
        query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books=LitePal.findAll(Book.class);
                for (Book book:books){
                    Log.i(TAG, "book name: "+book.getName());
                    Log.i(TAG, "book author: "+book.getAuthor());
                    Log.i(TAG, "book pages: "+book.getPages());
                    Log.i(TAG, "book price: "+book.getPrice());
                    Log.i(TAG, "book press: "+book.getPress());
                }

            }
        });
    }
}
