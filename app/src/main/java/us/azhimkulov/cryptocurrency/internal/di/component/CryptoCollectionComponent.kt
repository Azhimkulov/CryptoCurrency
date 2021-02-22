package us.azhimkulov.cryptocurrency.internal.di.component

import dagger.Component
import us.azhimkulov.cryptocurrency.internal.di.PerActivity
import us.azhimkulov.cryptocurrency.internal.di.module.ActivityModule
import us.azhimkulov.cryptocurrency.internal.di.module.CryptoCollectionModule
import us.azhimkulov.cryptocurrency.view.fragment.CryptoCollectionFragment
import us.azhimkulov.cryptocurrency.view.viewmodel.factory.CryptoCollectionViewModelFactory

/**
 * Created by azamat  on 2/21/21.
 */

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class, CryptoCollectionModule::class]
)
interface CryptoCollectionComponent {
    fun inject(cryptoCollectionFragment: CryptoCollectionFragment)
    fun inject(cryptoCollectionViewModelFactory: CryptoCollectionViewModelFactory)
}