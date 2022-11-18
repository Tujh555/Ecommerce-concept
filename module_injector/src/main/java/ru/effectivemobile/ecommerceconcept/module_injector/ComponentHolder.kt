package ru.effectivemobile.ecommerceconcept.module_injector

interface ComponentHolder<A : BaseApi, D : BaseDependencies> {
    fun init(dependencies: D)
    fun get(): A
    fun reset()
}