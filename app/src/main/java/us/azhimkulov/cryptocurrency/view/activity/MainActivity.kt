package us.azhimkulov.cryptocurrency.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_with_fragment.*
import us.azhimkulov.cryptocurrency.R
import us.azhimkulov.cryptocurrency.internal.di.HasComponent
import us.azhimkulov.cryptocurrency.internal.di.component.CryptoCollectionComponent
import us.azhimkulov.cryptocurrency.internal.di.component.DaggerCryptoCollectionComponent
import us.azhimkulov.cryptocurrency.internal.di.module.CryptoCollectionModule
import us.azhimkulov.cryptocurrency.view.fragment.CryptoCollectionFragment
import us.azhimkulov.cryptocurrency.view.viewmodel.CryptoCollectionViewModel

class MainActivity : BaseActivity(), HasComponent<CryptoCollectionComponent> {
    private var cryptoCollectionComponent: CryptoCollectionComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_fragment)

        initializeInjector()
        injectActivity()

        replaceFragment(container_for_fragment.id, CryptoCollectionFragment.newInstance())
    }

    override fun getComponent(): CryptoCollectionComponent {
        if (cryptoCollectionComponent == null) {
            initializeInjector()
        }
        return cryptoCollectionComponent!!
    }

    private fun injectActivity() {
        getApplicationComponent().inject(this)
    }

    private fun initializeInjector() {
        this.cryptoCollectionComponent = DaggerCryptoCollectionComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .cryptoCollectionModule(CryptoCollectionModule())
            .build()
    }
}