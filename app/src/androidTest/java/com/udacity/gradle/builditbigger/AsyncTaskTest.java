package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by Audi on 03/01/17.
 */

public class AsyncTaskTest extends AndroidTestCase {
    private static final String LOG_TAG = "AsyncTaskTest";

    public void test() {

        Log.v(LOG_TAG, "Running NonEmptyStringTest test");
        String result = null;
        GetJokeFromServer endpointsAsyncTask = new GetJokeFromServer(getContext());
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(LOG_TAG, "Response from server: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}
