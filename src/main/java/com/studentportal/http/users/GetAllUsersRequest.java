package com.studentportal.http.users;

import com.studentportal.http.HttpRequest;
import com.studentportal.http.RequestHandler;
import com.studentportal.user.User;
import com.studentportal.user.UserHelper;
import com.studentportal.user.UserRole;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class GetAllUsersRequest implements HttpRequest<List<User>, UserRole> {
    @Override
    public List<User> makeRequest(RequestHandler callback, UserRole userRole) {
        String json = null;
        if (callback == null) {
            System.out.println("callback is null");
        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpGet httpGet = new HttpGet("http://localhost:9990/users/all/" +
                        userRole.name());
                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                    @Override
                    public String handleResponse(final HttpResponse response) throws IOException {
                        int status = response.getStatusLine().getStatusCode();
                        String s = null;
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            if (entity != null) {
                                s = EntityUtils.toString(entity);
                                callback.onSuccess();
                            }
                        } else {
                            callback.onFailure(new ClientProtocolException(
                                    "Unexpected response status: " + status));
                        }
                        return s;
                    }
                };
                json = httpClient.execute(httpGet, responseHandler);
            } catch (ClientProtocolException e) {
                callback.onFailure(e);
            } catch (IOException e) {
                callback.onFailure(e);
            } finally {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return UserHelper.extractUserListFromJson(json);
    }
}
