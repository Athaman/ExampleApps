package com.athaman.whatstheweather;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText inputView;
    TextView resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = (EditText) findViewById(R.id.inputView);
        resultView = (TextView) findViewById(R.id.resultView);
    }

    public void search(View view){
        DownloadTask task = new DownloadTask();

        resultView.setText("");



        InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(inputView.getWindowToken(), 0);

        String request = URLEncoder.encode(inputView.getText().toString());

        task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + request
                 + "&appid=e803aa97c57e0888a6c594ff8da6f221");

    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection = null;
            char current;
            int data;
            try{
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                do {
                    data = reader.read();
                    current = (char) data;
                    result += current;
                }while(data != -1);
                in.close();
                reader.close();
                return result;
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            JSONObject jsonPart;

            try{
                JSONObject jsonObject = new JSONObject(result);
                String weatherInfo = jsonObject.getString("weather");
                JSONArray jsonArray = new JSONArray(weatherInfo);
                String weather = "";

                for(int i = 0; i < jsonArray.length(); i++){
                    jsonPart = jsonArray.getJSONObject(i);
                    weather +=(jsonPart.getString("main"));
                    weather += ": ";
                    weather +=(jsonPart.getString("description"));
                    weather += ". ";
                }

                resultView.setText(weather);


            }catch(JSONException e){
                e.printStackTrace();
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "That doesn't appear to be a place",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
