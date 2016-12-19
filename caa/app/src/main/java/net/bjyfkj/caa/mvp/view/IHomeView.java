package net.bjyfkj.caa.mvp.view;

import net.bjyfkj.caa.entity.IndexInfo;

/**
 * Created by YFKJ-1 on 2016/12/17.
 */

public interface IHomeView {
    String getToken();

    void indexError();

    void timeout();

    void hidell_release_null();

    void hidell_home_release();

    void hidell_home_ring();

    void showll_release_null();

    void showll_home_release();

    void showll_home_ring(IndexInfo.DataBean index);

}
