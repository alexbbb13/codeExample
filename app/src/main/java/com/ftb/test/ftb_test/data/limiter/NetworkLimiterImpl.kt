package com.ftb.test.ftb_test.data.limiter

import com.ftb.test.ftb_test.data.models.Constants

class NetworkLimiterImpl: NetworkLimiter {
    companion object {
        var lastMatchUpdated = 0L;
        var lastPredictionUpdated = 0L;
    }

    override fun isMatchNetworkLimited():Boolean {
        return (System.currentTimeMillis() - lastMatchUpdated) < Constants.UPDATE_INTERVAL
    }

    override fun isPredictionNetworkLimited():Boolean {
        return (System.currentTimeMillis() - lastPredictionUpdated) < Constants.UPDATE_INTERVAL
    }

    override fun resetMatchNetworkLimit() {
        lastMatchUpdated = System.currentTimeMillis()
    }

    override fun resetPredictionNetworkLimit() {
        lastPredictionUpdated = System.currentTimeMillis()
    }
}