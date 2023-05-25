package com.samsung.reliab.data.api.sites;

import com.samsung.reliab.data.api.RetrofitService;


public class SitesApiService {

    private static SitesApi sitesApi;

    private static SitesApi create() {
        return RetrofitService.getInstance().create(SitesApi.class);
    }

    public static SitesApi getInstance() {
        if (sitesApi == null) sitesApi = create();
        return sitesApi;
    }

}