package com.samsung.reliab.data.repository;

import retrofit2.Call;

import com.samsung.reliab.data.api.sites.SitesApiService;
import com.samsung.reliab.domain.model.Sites;

import java.util.List;

public class SitesRepository {

    public static Call<List<Sites>> getSites() {
        return SitesApiService.getInstance().getSites();
    }

    public static Call<Sites> getSite(long id) {
        return SitesApiService.getInstance().getSite(id);
    }

    public static Call<Void> deleteSite(long id) {
        return SitesApiService.getInstance().deleteSite(id);
    }
}
