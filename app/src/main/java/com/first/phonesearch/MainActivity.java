package com.first.phonesearch;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;



import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String URL="https://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=";
    private EditText etText;
    private Button btnSend;
    private TextView tvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend=(Button)findViewById(R.id.btn_send);
        tvShow=(TextView)findViewById(R.id.tv_show);
        etText=(EditText)findViewById(R.id.et_text);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.get(URL + etText.getText().toString())
                        .tag(this)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String jsonData, Call call, Response response) {
                                //    Toast.makeText(MainActivity.this,s.toString(),Toast.LENGTH_SHORT).show();
//                                tvShow.setText(jsonData.toString());
//                                      parseJSONWithGSON(s,);
                         /*      // json解析器，解析json数据
                                JsonParser parser = new JsonParser();
                                JsonElement element = parser.parse(jsonData);
                                // json属于对象类型时
                                if (element.isJsonObject()) {
                                    JsonObject object = element.getAsJsonObject();  // 转化为对象

                                    JsonObject response1=object.getAsJsonObject("response");
                                    JsonObject Tel=response1.getAsJsonObject("tel");
                                    String location=Tel.get("location").getAsString();

                                }*/
                                jsontojava(jsonData,etText.getText().toString());
                            }
                        });
            }
        });
    }
    private void jsontojava(String jsonData,String tel)
    {
        // json解析器，解析json数据
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonData);
        // json属于对象类型时
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();  // 转化为对象

            JsonObject response=object.getAsJsonObject("response");
            JsonObject Tel=response.getAsJsonObject(tel);
            String location=Tel.get("location").getAsString();
            tvShow.setText(location);
        }
    }
}

