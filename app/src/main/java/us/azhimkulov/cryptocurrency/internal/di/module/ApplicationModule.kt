package us.azhimkulov.cryptocurrency.internal.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import us.azhimkulov.cryptocurrency.AndroidApplication
import us.azhimkulov.cryptocurrency.UIThread
import us.azhimkulov.data.executor.JobExecutor
import us.azhimkulov.data.rest.RestClient
import us.azhimkulov.data.rest.RestClientImpl
import us.azhimkulov.domain.executor.PostExecutionThread
import us.azhimkulov.domain.executor.ThreadExecutor
import javax.inject.Singleton

/**
 * Created by azamat  on 2/21/21.
 */

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideRestClient(): RestClient {
        return RestClientImpl("https://api.coinstats.app/public/v1/")
    }
}