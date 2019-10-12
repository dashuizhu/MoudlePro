package com.test.module_message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.basemoudle.utils.ActivityUtils;

@Route(path = "/message/messageActivity")
public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        //findViewById(R.id.tv_message).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        ActivityUtils.startLogin();
        //    }
        //});
    }
}
