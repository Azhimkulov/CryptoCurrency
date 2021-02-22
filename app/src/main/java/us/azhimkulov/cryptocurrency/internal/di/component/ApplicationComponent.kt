package us.azhimkulov.cryptocurrency.internal.di.component

import android.content.Context
import dagger.Component
import us.azhimkulov.cryptocurrency.internal.di.module.ApplicationModule
import us.azhimkulov.cryptocurrency.view.activity.MainActivity
import us.azhimkulov.data.persistence.realm.executor.RxRealmExecutorsProvider
import us.azhimkulov.data.persistence.realm.provider.RealmProvider
import us.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import us.azhimkulov.data.rest.RestClient
import us.azhimkulov.data.source.CryptoLocalDataBehaviour
import us.azhimkulov.domain.executor.PostExecutionThread
import us.azhimkulov.domain.executor.ThreadExecutor
import javax.inject.Singleton

/**
 * Created by azamat  on 2/21/21.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun context(): Context
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun restClient(): RestClient
    fun rxRealmExecutorsProvider(): RxRealmExecutorsProvider
    fun realmUnitOfWorkFactory(): RealmUnitOfWorkFactory
    fun realmProvider(): RealmProvider
    fun cryptoLocalDataBehaviour(): CryptoLocalDataBehaviour
}