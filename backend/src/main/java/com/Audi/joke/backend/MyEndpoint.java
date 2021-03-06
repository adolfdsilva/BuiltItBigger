/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.Audi.joke.backend;

import com.example.Joke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "getJoke",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.joke.Audi.com",
                ownerName = "backend.joke.Audi.com"
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        return new Joke();
    }
}
