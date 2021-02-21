package us.azhimkulov.cryptocurrency.internal.di.component

import dagger.Component
import us.azhimkulov.cryptocurrency.internal.di.PerActivity
import us.azhimkulov.cryptocurrency.internal.di.module.ActivityModule

/**
 * Created by azamat  on 2/21/21.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
}