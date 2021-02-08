package com.zaurfarrukhzada.carannouncementmobileproject.view.Activity.AllBrands;

import com.zaurfarrukhzada.carannouncementmobileproject.interactors.AllBrandsInteract;
import com.zaurfarrukhzada.carannouncementmobileproject.model.AllCarBrand;

import java.util.List;

public class AllBrandsPresenter implements IAllBrandsContract.Presenter{

    IAllBrandsContract.View mView;
    AllBrandsInteract allBrandsInteract;

    public AllBrandsPresenter(IAllBrandsContract.View mView) {
        this.mView = mView;
        this.allBrandsInteract = new AllBrandsInteract(this);
    }

    @Override
    public void created() {
        this.mView.initalize();
        this.mView.callAllBrands();
        this.mView.recycleConfig();
    }

    @Override
    public void getAllBrands() {
        this.mView.showDialogLoading();
        this.allBrandsInteract.callAllBrandList();
    }

    @Override
    public void success(List<AllCarBrand> body) {
        this.mView.hideDialogLoading();
        this.mView.getDataSuccess(body);
    }


}
