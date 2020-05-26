package org.tensorflow.lite.examples.detection.customModels.http;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    /**
     *
     * This method was written using: https://dzone.com/articles/how-to-implement-get-and-post-request-through-simp
     * @param link
     * @return
     */
    public String get (String link) throws IOException {

        URL url = new URL(link);

        //Setup connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        //Set Http Basic Authentication Header
        connection.setRequestProperty  ("Authorization", "Basic ");

        int responseCode = connection.getResponseCode();
        String httpResponse = "";

        if (responseCode == HttpURLConnection.HTTP_OK) {

            //Retrieve the call's input stream response
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            //IOUtils is an external JAR that is able to convert inputstream objects to string
            httpResponse = IOUtils.toString(inputStream, "UTF-8");

            if(inputStream != null)
                inputStream.close();

            if(connection != null)
                connection.disconnect();
        } else {
            System.out.println("Failed to receive HTTP GET response");
        }

        return httpResponse;
    }
}
