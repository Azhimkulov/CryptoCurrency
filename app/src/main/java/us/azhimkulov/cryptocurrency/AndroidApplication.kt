package us.azhimkulov.cryptocurrency

import android.app.Application
import io.realm.Realm
import us.azhimkulov.cryptocurrency.internal.di.component.ApplicationComponent
import us.azhimkulov.cryptocurrency.internal.di.component.DaggerApplicationComponent
import us.azhimkulov.cryptocurrency.internal.di.module.ApplicationModule

/**
 * Created by azamat  on 2/21/21.
 */
class AndroidApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializePersistenceStorage()
        initializeInjector()
    }

    private fun initializePersistenceStorage() {
        Realm.init(this)
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}