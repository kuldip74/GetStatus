package com.kuldeep.getstatus;

import android.support.annotation.NonNull;

/**
 * Created by ADMIN on 2/22/2018.
 */

public class UserId {

    public String userId;

    public<T extends UserId> T withId(@NonNull final String id){

        this.userId = id;
        return (T) this;
    }
}
