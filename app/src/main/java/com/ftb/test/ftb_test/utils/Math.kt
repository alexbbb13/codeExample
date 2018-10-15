package com.ftb.test.ftb_test.utils

class Math(){
    companion object {
        inline fun hash(i: Int, j: Int): Int {
            return i.xor(j)
        }

        inline fun hash(i: String, j: String): Int {
            return i.hashCode().xor(j.hashCode())
        }
    }
}
