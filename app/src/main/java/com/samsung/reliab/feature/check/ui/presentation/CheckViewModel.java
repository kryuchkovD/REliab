package com.samsung.reliab.feature.check.ui.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samsung.reliab.data.repository.SitesRepository;
import com.samsung.reliab.domain.model.Sites;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckViewModel extends ViewModel {
    private final MutableLiveData<CheckStatus> _status = new MutableLiveData<>();
    public LiveData<CheckStatus> status = _status;
    private final MutableLiveData<Sites> _site = new MutableLiveData<>();
    public LiveData<Sites> site = _site;

    public void load(long id) {
        _status.setValue(CheckStatus.LOADING);
        SitesRepository.getSite(id).enqueue(new Callback<Sites>() {
            @Override
            public void onResponse(@NonNull Call<Sites> call, @NonNull Response<Sites> response) {
                _status.setValue(CheckStatus.LOADED);
                _site.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Sites> call, @NonNull Throwable throwable) {
                _status.setValue(CheckStatus.FAILURE);
                throwable.printStackTrace();
            }
        });
}
    public void delete(long id) {
        _status.setValue(CheckStatus.LOADING);
        SitesRepository.deleteSite(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,@NonNull Response<Void> response) {
                _status.setValue(CheckStatus.DELETED);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                _status.setValue(CheckStatus.FAILURE);
                throwable.printStackTrace();
            }
        });
    }
}
