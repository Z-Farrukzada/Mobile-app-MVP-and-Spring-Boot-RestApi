package com.zaurfarrukhzada.carannouncementmobileproject.interactors;

import com.zaurfarrukhzada.carannouncementmobileproject.model.Announcement;
import com.zaurfarrukhzada.carannouncementmobileproject.restApi.ManagerAll;
import com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home.MainActivityPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementInteract {

    MainActivityPresenter mainActivityPresenter;

    public AnnouncementInteract(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    public void getCallPopAnnouncement() {
        Call<List<Announcement>> call = ManagerAll.getInstance().getCallAnnouncementPop();

        call.enqueue(new Callback<List<Announcement>>() {
            @Override
            public void onResponse(@NotNull Call<List<Announcement>> call, @NotNull Response<List<Announcement>> response) {
                mainActivityPresenter.successAnnouncement(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Announcement>> call, @NotNull Throwable t) {

            }
        });
    }
}
