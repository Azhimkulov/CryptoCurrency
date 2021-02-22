package us.azhimkulov.data.persistence.realm.executor

/**
 * Created by azamat  on 2020-07-27.
 */
interface RxRealmExecutorsProvider {
    fun provideSingleItemQueryExecutor(): RxRealmSingleQueryExecutor
    fun provideMultipleItemsQueryExecutor(): RxRealmMultipleItemsQueryExecutor
    fun provideTransactionExecutor(): RxRealmTransactionExecutor
}