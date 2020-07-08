package com.ftb.test.pokemon.data.limiter

interface NetworkLimiter {
    fun isMatchNetworkLimited():Boolean
    fun isPredictionNetworkLimited():Boolean
    fun resetMatchNetworkLimit()
    fun resetPredictionNetworkLimit()
}