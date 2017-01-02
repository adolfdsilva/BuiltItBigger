package audi.com.ajokelib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Audi on 29/12/16.
 */

public class TellJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell_joke);


        String joke = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        TextView tvJoke = ((TextView) findViewById(R.id.tvJoke));
        if (joke != null)
            tvJoke.setText(joke);
        else
            tvJoke.setText(getString(R.string.default_joke));

    }
}
