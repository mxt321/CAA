package net.bjyfkj.caa.mvp.presenter;

import net.bjyfkj.caa.mvp.model.ringchoose.IRingChooseModel;
import net.bjyfkj.caa.mvp.model.ringchoose.OnGetCityLinstener;
import net.bjyfkj.caa.mvp.model.ringchoose.RingChooseModel;
import net.bjyfkj.caa.mvp.view.IRingChooseView;

import java.util.List;
import java.util.Map;

/**
 * Created by YFKJ-1 on 2016/12/19.
 */

public class RingChoosePresenter {
    private IRingChooseModel iRingChooseModel;
    private IRingChooseView iRingChooseView;

    public RingChoosePresenter(IRingChooseView iRingChooseView) {
        this.iRingChooseModel = new RingChooseModel();
        this.iRingChooseView = iRingChooseView;
    }

    /**
     * 获取城市列表
     */
    public void getCity() {
        iRingChooseModel.getCity(iRingChooseView.getToken(), new OnGetCityLinstener() {
            @Override
            public void Success(List<Map<String, String>> list) {
                iRingChooseView.hidell_hot_area();
                iRingChooseView.hidell_hot_districts();
                iRingChooseView.Success(0, list);
            }

            @Override
            public void Error() {
                iRingChooseView.Error();
            }

            @Override
            public void Timeout() {
                iRingChooseView.Timeout();
            }
        });
    }

    /***
     * 获取区域列表
     */
    public void getDistricts() {
        iRingChooseModel.getDistricts(iRingChooseView.getToken(), iRingChooseView.getCity(), new OnGetCityLinstener() {
            @Override
            public void Success(List<Map<String, String>> list) {
                iRingChooseView.hidell_hot_area();
                iRingChooseView.showll_hot_districts();
                iRingChooseView.Success(1, list);
            }

            @Override
            public void Error() {
                iRingChooseView.Error();
            }

            @Override
            public void Timeout() {
                iRingChooseView.Timeout();
            }
        });
    }

    /**
     * 获取商圈列表
     */
    public void getArea() {
        iRingChooseModel.getArea(iRingChooseView.getToken(), iRingChooseView.getCity(), iRingChooseView.getStrict(), new OnGetCityLinstener() {
            @Override
            public void Success(List<Map<String, String>> list) {
                iRingChooseView.showll_hot_area();
                iRingChooseView.Success(2, list);
            }

            @Override
            public void Error() {
                iRingChooseView.Error();
            }

            @Override
            public void Timeout() {
                iRingChooseView.Timeout();
            }
        });
    }

}
