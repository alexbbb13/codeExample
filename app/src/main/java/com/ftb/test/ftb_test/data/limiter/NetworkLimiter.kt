package com.ftb.test.ftb_test.data.limiter

interface NetworkLimiter {
    fun isLimited():Boolean
    fun resetLimit()
}