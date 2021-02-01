package com.zaurfarrukhzada.carannouncementmobileproject.restApi;

public class BaseManager {

    protected RestApi getRestApiClient(){
        RestApiClient restApiClient = new RestApiClient(BaseUrl.BASE_URL);
        return restApiClient.getRestApi();
    }
}
