package com.ftb.test.ftb_test.data.limiter

import android.provider.SyncStateContract
import com.ftb.test.ftb_test.data.models.Constants

class NetworkLimiterImpl: NetworkLimiter {
    companion object {
        var lastUpdated = 0L;
    }

    override fun isLimited():Boolean {
        return (System.currentTimeMillis() - lastUpdated) < Constants.UPDATE_INTERVAL
    }

    override fun resetLimit() {
        lastUpdated = System.currentTimeMillis()
    }
}