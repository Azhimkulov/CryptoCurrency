package us.azhimkulov.cryptocurrency.internal.di

/**
 * Created by azamat  on 2/21/21.
 */
interface HasComponent<C> {
    fun getComponent(): C
}