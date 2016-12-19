package net.bjyfkj.caa.mvp.model.home;

import net.bjyfkj.caa.entity.IndexInfo;

/**
 * Created by YFKJ-1 on 2016/12/17.
 */

public interface OnUserIndexLinstener {
    //    void ll_release_null();
//
//    void ll_home_release();
//
//    void ll_home_ring();
    void indexSuccess(IndexInfo.DataBean index);

    void indexError();

    void timeout();
}
