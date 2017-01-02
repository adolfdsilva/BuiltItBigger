package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.audi.joke.backend.getJoke.GetJoke;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import audi.com.ajokelib.TellJokeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void tellJoke(View view) {
        new GetJokeFromServer().execute();

    }


    class GetJokeFromServer extends AsyncTask<Void, Void, String> {
        private GetJoke myApiService = null;

        @Override
        protected String doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                GetJoke.Builder builder = new GetJoke.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                myApiService = builder.build();
            }


            try {
                return myApiService.getJoke().execute().getFirstJoke();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Joke",result);
            if (result != null) {
                Intent intent = new Intent(getApplicationContext(), TellJokeActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, result);
                startActivity(intent);
            } else {
                //TODO toast error
            }

        }
    }

}
