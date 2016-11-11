package org.wso2.bleagent.transport.executor;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wso2123 on 11/10/16.
 */
public class ManagerRequestAsyncExecutor extends AsyncTask<String, Void, Boolean> {
    @Override
    protected Boolean doInBackground(String... strings) {
        boolean status = false;

        String managerUrl = strings[0];
        String[] endpointAttributes = strings[1].split(";");

        String requestType = endpointAttributes[0];
        String requestUrl = endpointAttributes[1];
        String requestParams = endpointAttributes[2];

        try {
            URL url = new URL(managerUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String postData = generateEncodedAttribute("type", requestType) + "&" +
                                generateEncodedAttribute("url", requestUrl) + "&" +
                                generateEncodedAttribute("params", requestParams);
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            status = true;

            bufferedReader.close();
            inputStream.close();
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    private String generateEncodedAttribute(String name, String value) throws UnsupportedEncodingException {
        String encodedAttribute = URLEncoder.encode(name, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
        return encodedAttribute;
    }
}
