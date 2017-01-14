package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.entity.WxUserEntity;
import net.bjyfkj.caa.mvp.model.getuser.GetUserModel;
import net.bjyfkj.caa.mvp.model.getuser.IGetUserModel;
import net.bjyfkj.caa.mvp.model.getuser.OnGetWxUserPromotionLinstener;
import net.bjyfkj.caa.mvp.view.IGetUserView;

/**
 * Created by YFKJ-1 on 2017/1/13.
 */

public class GetUserPresenter {
    private IGetUserView iGetUserView;
    private IGetUserModel iGetUserModel;

    public GetUserPresenter(IGetUserView iGetUserView) {
        this.iGetUserView = iGetUserView;
        iGetUserModel = new GetUserModel();
    }

    /**
     * #获取领取优惠券的微信用户
     */
    public void getWxUserPromotion() {
        iGetUserView.clearColor();
        if (iGetUserView.getStatus().equals("0")) {
            iGetUserView.tvNoGetColor();
        } else if (iGetUserView.getStatus().equals("1")) {
            iGetUserView.tvUseColor();
        } else if (iGetUserView.getStatus().equals("2")) {
            iGetUserView.tvExpiredColor();
        }
        iGetUserModel.getWxUserPromotion(iGetUserView.getToken(), iGetUserView.getAdsId(), iGetUserView.getFrom(), new OnGetWxUserPromotionLinstener() {
            @Override
            public void getWxUserPromotionSuccess(WxUserEntity wxUserEntity) {
                iGetUserView.getWxUserPromotionSuccess(wxUserEntity);
            }

            @Override
            public void getWxUserPromotionError() {
                iGetUserView.getWxUserPromotionError();
            }

            @Override
            public void timeout() {
                iGetUserView.timeout();
            }
        });
    }

}
