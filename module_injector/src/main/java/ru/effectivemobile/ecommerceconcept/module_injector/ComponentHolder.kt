package ru.effectivemobile.ecommerceconcept.module_injector

/**
 * A basic interface for component holders,,
 * delivers dependencies to the graph and provides an feature's API
 */
interface ComponentHolder<A : BaseApi, D : BaseDependencies> {
    /**
     * Method for initialization of component
     *
     * @param dependencies - component dependencies
     */
    fun init(dependencies: D)

    /**
     * Getting feature's API
     *
     * @return A - feature's API
     */
    fun get(): A

    /**
     * Method for release a component
     */
    fun reset()
}