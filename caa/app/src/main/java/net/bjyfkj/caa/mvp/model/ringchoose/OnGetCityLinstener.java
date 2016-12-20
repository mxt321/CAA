package net.bjyfkj.caa.mvp.model.ringchoose;

import java.util.List;
import java.util.Map;

/**
 * Created by YFKJ-1 on 2016/12/20.
 */

public interface OnGetCityLinstener {
    void Success(List<Map<String, String>> list);

    void Error();

    void Timeout();
}
