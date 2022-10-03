package dev.thenoveltyseeker.articlestime.domain


interface NullableListDataMapper<I, O> {

    fun map(list: List<I>?): List<O>
}

interface ListDataMapper<I, O> {

    fun map(list: List<I>): List<O>
}