package net.bjyfkj.caa.mvp.model.myrelease;

import net.bjyfkj.caa.entity.MyReleaseEntity;

/**
 * Created by YFKJ-1 on 2017/1/14.
 */

public interface MyReleaseLinstener {
    void MyReleaseSuccess(MyReleaseEntity myReleaseEntity);

    void MyReleaseError();

    void timeout();
}
