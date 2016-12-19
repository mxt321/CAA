package net.bjyfkj.caa.mvp.model.me;

/**
 * Created by YFKJ-1 on 2016/12/16.
 */

public interface OnUpHeadlinstener {
    void upHeanSuccess(String urlpath);

    void upHeadError();

    void upHeadTimeout();
}
