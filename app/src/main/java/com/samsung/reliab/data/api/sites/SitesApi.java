package com.samsung.reliab.data.api.sites;

import com.samsung.reliab.domain.model.Sites;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SitesApi {

    @GET("sites")
    Call<List<Sites>> getSites();

    @GET("sites/{id}")
    Call<Sites> getSite(
            @Path("id")
            long id
    );
    @DELETE("sites/{id}")
    Call<Void> deleteSite(long id);

}
