package us.azhimkulov.cryptocurrency.internal.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import us.azhimkulov.cryptocurrency.internal.di.PerActivity

/**
 * Created by azamat  on 2/21/21.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return activity
    }
}