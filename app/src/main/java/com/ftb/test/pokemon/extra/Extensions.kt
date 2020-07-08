package com.ftb.test.pokemon.extra

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun extraKey() = object : ReadOnlyProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "${thisRef?.javaClass?.canonicalName ?: ""}-${property.name}"
    }
}
