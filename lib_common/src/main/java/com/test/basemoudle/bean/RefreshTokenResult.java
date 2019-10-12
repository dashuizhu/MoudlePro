package com.test.basemoudle.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhuj 2019/6/4 上午11:03.
 */
public class RefreshTokenResult extends NetworkResult {

    /**
     * data : {"accessToken":"40936a3c-219c-4e45-8a3c-e27059759023","refreshToken":"2f1c7881-b859-469f-b6c9-fbd8854719db","expiresIn":35999}
     */

    private AuthBean data;

    public AuthBean getData() {
        return data;
    }

    public void setData(AuthBean data) {
        this.data = data;
    }
}
