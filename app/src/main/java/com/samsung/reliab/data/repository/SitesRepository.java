package com.samsung.reliab.data.repository;

import retrofit2.Call;

import com.samsung.reliab.data.api.sites.SitesApiService;
import com.samsung.reliab.domain.model.Sites;


public class SitesRepository {

    public static Call<Sites> getName() {
        return SitesApiService.getInstance().getName();
    }

    public static Call<Sites> getUrl() {
        return SitesApiService.getInstance().getUrl();
    }

    public static Call<Sites> getStatus() {
        return SitesApiService.getInstance().getStatus();
    }

    public static Call<Sites> getSite(long id) {
        return SitesApiService.getInstance().getSite(id);
    }

    public static Call<Void> deleteSite(long id) {
        return SitesApiService.getInstance().deleteSite(id);
    }
}
