package us.azhimkulov.cryptocurrency.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import us.azhimkulov.cryptocurrency.R
import us.azhimkulov.cryptocurrency.databinding.FragmentCryptoCollectionBinding
import us.azhimkulov.cryptocurrency.internal.di.component.CryptoCollectionComponent
import us.azhimkulov.cryptocurrency.view.viewmodel.CryptoCollectionViewModel
import us.azhimkulov.cryptocurrency.view.viewmodel.LoadingViewModel
import us.azhimkulov.cryptocurrency.view.viewmodel.factory.CryptoCollectionViewModelFactory
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class CryptoCollectionFragment : BaseFragment() {

    @Inject
    lateinit var cryptoCollectionViewModelFactory: CryptoCollectionViewModelFactory
    private val cryptoCollectionViewModel: CryptoCollectionViewModel by activityViewModels { cryptoCollectionViewModelFactory }
    private lateinit var binding: FragmentCryptoCollectionBinding

    companion object {
        @JvmStatic
        fun newInstance(): CryptoCollectionFragment {
            return CryptoCollectionFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(CryptoCollectionComponent::class.java).inject(this)
        lifecycle.addObserver(cryptoCollectionViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_crypto_collection,
                container,
                false
            )
        binding.viewModel = cryptoCollectionViewModel
        return binding.root
    }

    override fun provideLoadingViewModel(): LoadingViewModel {
        return cryptoCollectionViewModel
    }
}