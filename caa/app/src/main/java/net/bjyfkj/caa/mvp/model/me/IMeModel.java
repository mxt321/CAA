package net.bjyfkj.caa.mvp.model.me;

/**
 * Created by YFKJ-1 on 2016/12/16.
 */

public interface IMeModel {
    void logout(String token, OnLogoutLinstener onLogoutLinstener);

    void upHead(String token, String photo, OnUpHeadlinstener onUpHeadlinstener);
}
