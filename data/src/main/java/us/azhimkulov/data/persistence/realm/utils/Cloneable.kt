package us.azhimkulov.data.persistence.realm.utils

/**
 * Created by azamat  on 2020-07-27.
 */
interface Cloneable<T> {
    fun makeShallowCopy(): T
}