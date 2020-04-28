package com.example.checkout_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView2;


//   public class DownloadTask extends AsyncTask<String,Void,String> {
//
//       @Override
//       protected String doInBackground(String... urls) {
//           String result="";
//           URL url;
//           HttpURLConnection connection=null;
//           try {
//               url = new URL(urls[0]);
//               connection = (HttpURLConnection) url.openConnection();
//               InputStream is = connection.getInputStream();
//               InputStreamReader reader = new InputStreamReader(is);
//               int data = reader.read();
//
//           while (data !=-1){
//               char current=(char) data;
//               result +=current;
//               data=reader.read();
//
//           }
//           return result;
//
//           } catch (MalformedURLException e) {
//               e.printStackTrace();
//           } catch (IOException e) {
//               e.printStackTrace();
//           }
//
//
//           return null;
//       }
//
//       @Override
//       protected void onPostExecute(String string) {
//           super.onPostExecute(string);
//        try{
//            JSONObject jsonObject=new JSONObject();
//            String weatherInfo=jsonObject.getString("weather");
//            JSONArray array=new JSONArray(weatherInfo);
//
//            for(int i=0;i<array.length();i++){
//                JSONObject jsonpart=array.getJSONObject(i);
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//       }
//   }

    //


//        DownloadTask downloadTask=new DownloadTask();
//        downloadTask.execute("https://openweathermap.org/data/2.5/weather?q=" + editText.getText().toString() + "&appid=jeckic87");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        textView2=findViewById(R.id.textView2);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherbyCity(editText.getText().toString());
            }
        });

    }

    public void getWeatherbyCity(String name){
        Map<String, String> query = new HashMap<>();
        //TODO unesi api key
        query.put("apikey", "7ffe3e20064e7d27b6e7f96873c93c77");
        query.put("q", name.trim());

        Call<Example> call = MyService.apiInterface().getWeather(query);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.code() == 200) {
                    try {
                        Example searches = response.body();

                        ArrayList<Weather> search = new ArrayList<>();

                        for (Weather e : searches.getWeather()) {

                            //if (e.getDescription().equals("weather")) {
                            search.add(e);


                            textView2.setText(e.getDescription());
                            textView2.setText(e.getMain());
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
