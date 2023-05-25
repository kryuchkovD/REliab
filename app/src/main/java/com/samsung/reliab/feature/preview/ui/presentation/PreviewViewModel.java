package com.samsung.reliab.feature.preview.ui.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samsung.reliab.data.repository.SitesRepository;
import com.samsung.reliab.domain.model.Sites;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviewViewModel extends ViewModel {

    private MutableLiveData<PreviewStatus> _status = new MutableLiveData<>();
    public LiveData<PreviewStatus> status = _status;
    private MutableLiveData<List<Sites>> _sites = new MutableLiveData<>();
    public LiveData<List<Sites>> sites = _sites;

    public void load() {
        _status.setValue(PreviewStatus.LOADING);
        SitesRepository.getSites().enqueue(new Callback<List<Sites>>() {
            @Override
            public void onResponse(@NonNull Call<List<Sites>> call, @NonNull Response<List<Sites>> response) {
                _status.setValue(PreviewStatus.LOADED);
                _sites.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Sites>> call, @NonNull Throwable throwable) {
                _status.setValue(PreviewStatus.FAILURE);
                throwable.printStackTrace();
            }
        });
    }

}
