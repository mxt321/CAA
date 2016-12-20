package net.bjyfkj.caa.mvp.view;

import java.util.List;
import java.util.Map;

/**
 * Created by YFKJ-1 on 2016/12/19.
 */

public interface IRingChooseView {
    String getToken();

    String getCity();

    String getStrict();

    void hidell_hot_districts();

    void hidell_hot_area();

    void showll_hot_districts();

    void showll_hot_area();

    void Timeout();

    void Error();

    void Success(int status, List<Map<String, String>> list);// 0 -城市列表   1 -区域列表   2 -商圈列表
}
