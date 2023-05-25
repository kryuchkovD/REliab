package com.samsung.reliab.feature.preview.ui.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samsung.reliab.data.repository.SitesRepository;
import com.samsung.reliab.domain.model.Sites;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviewViewModel extends ViewModel {

    private final MutableLiveData<PreviewStatus> _status = new MutableLiveData<>();
    public LiveData<PreviewStatus> status = _status;
    private final MutableLiveData <Sites>  _sites = new MutableLiveData<Sites>();
    public LiveData<Sites> sites = _sites;

    public void load() {
        _status.setValue(PreviewStatus.LOADING);
        SitesRepository.getName().enqueue(new Callback<Sites>() {
            @Override
            public void onResponse(@NonNull Call<Sites> call, @NonNull Response<Sites> response) {
                _status.setValue(PreviewStatus.LOADED);
                _sites.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Sites> call, @NonNull Throwable throwable) {
                _status.setValue(PreviewStatus.FAILURE);
                throwable.printStackTrace();
            }
        });
    }

}
