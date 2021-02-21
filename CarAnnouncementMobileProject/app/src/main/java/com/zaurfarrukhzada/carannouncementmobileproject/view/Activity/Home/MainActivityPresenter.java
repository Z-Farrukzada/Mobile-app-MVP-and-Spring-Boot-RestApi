package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.Home;

import com.zaurfarrukhzada.carannouncementmobileproject.interactors.AnnouncementInteract;
import com.zaurfarrukhzada.carannouncementmobileproject.interactors.MainInteract;
import com.zaurfarrukhzada.carannouncementmobileproject.interactors.SliderInteract;
import com.zaurfarrukhzada.carannouncementmobileproject.model.Announcement;
import com.zaurfarrukhzada.carannouncementmobileproject.model.CarBrand;
import com.zaurfarrukhzada.carannouncementmobileproject.model.SliderItem;


import java.util.List;

public class MainActivityPresenter  implements IMainActivityContract.Presenter{

    IMainActivityContract.View mView;
    MainInteract mainInteract;
    SliderInteract sliderInteract;
    AnnouncementInteract announcementInteract;

    public MainActivityPresenter(IMainActivityContract.View mView) {
        this.mView = mView;
        this.mainInteract = new MainInteract(this);
        this.sliderInteract = new SliderInteract(this);
        this.announcementInteract = new AnnouncementInteract(this);
    }

    @Override
    public void created() {
        this.mView.init();
        this.mView.setDrawer();
        this.mView.brandRecycleConfig();
        this.mView.announcementConfig();
        this.mView.bottomNavConfig();
        this.mView.getAllBrands();
        this.mView.slideConfig();
    }

    @Override
    public void callAll() {
        this.mView.showDialogLoading();
        this.mainInteract.getCallAllBrands();
        this.sliderInteract.getAllSliders();

    }

    public void callAnnouncementPresenter() {
        this.mView.showDialogLoading();
        this.announcementInteract.getCallPopAnnouncement();
    }

    @Override
    public void success(List<CarBrand> body) {
        this.mView.hideDialogLoading();
        this.mView.onGetDataSuccess(body);
    }

    @Override
    public void successSlide(List<SliderItem> body) {
        this.mView.hideDialogLoading();
        this.mView.onGetDatSlider(body);
    }

    public void successAnnouncement(List<Announcement> body) {
        this.mView.GetDataAnnouncement(body);
    }


}
