package com.eos.youareheroine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class WriterPageActivity extends AppCompatActivity {

    private static final String TAG = "WriterPage";
    private RequestQueue queue;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer_page);
        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        String url = "https://my-json-server.typicode.com/candykick/apitest/writing";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<WriterPageData> dataList = gson.fromJson(response, new TypeToken<ArrayList<WriterPageData>>() {}.getType());

                RecyclerView wp_rv_list = findViewById(R.id.wp_rv_list);
                WPAdapter adapter = new WPAdapter(getApplicationContext(), dataList);
                wp_rv_list.setAdapter(adapter);
                wp_rv_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error handling. 토스트 메시지만 띄울 거임
                Toast.makeText(getApplicationContext(), "에러 발생 : " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERR", error.getLocalizedMessage());
            }
        });

        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }
}