package com.samsung.reliab.feature.main.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.samsung.reliab.R;
import com.samsung.reliab.data.api.sites.SitesApiService;
import com.samsung.reliab.domain.model.Sites;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SitesApiService.getInstance().getName().enqueue(new Callback<Sites>() {
            @Override
            public void onResponse(@NonNull Call<Sites> call, @NonNull Response<Sites> response) {
                assert response.body() != null;
                Log.d("DEBUG", response.body().toString());
            }

            @Override
            public void onFailure(@NonNull Call<Sites> call, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        SitesApiService.getInstance().getUrl().enqueue(new Callback<Sites>() {
            @Override
            public void onResponse(@NonNull Call<Sites> call, @NonNull Response<Sites> response) {
            }

            @Override
            public void onFailure(@NonNull Call<Sites> call, @NonNull Throwable t) {

            }
        });
        SitesApiService.getInstance().getStatus().enqueue(new Callback<Sites>() {
            @Override
            public void onResponse(@NonNull Call<Sites> call, @NonNull Response<Sites> response) {

            }

            @Override
            public void onFailure(@NonNull Call<Sites> call, @NonNull Throwable throwable) {

            }
        });
    }
}
