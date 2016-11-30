package com.example.dell.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.
        OnClickListener {

    private static final int UPDATE_TEXT = 1;

    private Button button;

    private TextView text;

    private Handler handler = new Handler(){
      public void handleMessage(Message msg){
          switch (msg.what){
              case UPDATE_TEXT:
                  //在这里可以进行UI操作
                  text.setText("Nice to meet you");
                  break;
              default:
                  break;
          }
      }
    };//Handler会受到Message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.change_text);
        text = (TextView) findViewById(R.id.text);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//将Message对象发送出去
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
