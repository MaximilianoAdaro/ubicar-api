package com.ubicar.ubicar.factories

interface AbstractFactory<T, V> {

  fun convert(input: T): V
}
