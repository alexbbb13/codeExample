package com.ftb.test.ftb_test.data.limiter

interface NetworkLimiter {
    fun isMatchNetworkLimited():Boolean
    fun isPredictionNetworkLimited():Boolean
    fun resetMatchNetworkLimit()
    fun resetPredictionNetworkLimit()
}