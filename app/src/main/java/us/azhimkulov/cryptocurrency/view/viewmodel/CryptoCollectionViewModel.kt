package us.azhimkulov.cryptocurrency.view.viewmodel

import androidx.databinding.ObservableField
import io.reactivex.observers.DisposableObserver
import us.azhimkulov.cryptocurrency.R
import us.azhimkulov.cryptocurrency.view.adapter.UltimateAdapter
import us.azhimkulov.domain.interactor.GetCrypts
import us.azhimkulov.domain.model.CryptoModel
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class CryptoCollectionViewModel @Inject constructor(
    private val getCrypts: GetCrypts
) : LoadingViewModel(), UltimateAdapter.UltimateAdapterDataSource {

    val ultimateAdapter = UltimateAdapter.newInstance()
    var isLoading: ObservableField<Boolean> = ObservableField(false)

    private val collection = mutableListOf<CryptoModel>()

    init {
        setupAdapter()
    }

    override fun onResume() {
        isLoading.set(true)
        getCrypts.execute(CryptsObserver())
    }

    override fun onDestroy() {
        getCrypts.dispose()
    }

    override fun recyclerView(): Int {
        return collection.size
    }

    override fun recyclerView(position: Int): CryptoModel {
        return collection[position]
    }

    private fun setupAdapter() {
        ultimateAdapter.register(R.layout.row_crypto, CryptoModel::class.java)
        ultimateAdapter.dataSource = this
    }

    private fun setCollection(models: Collection<CryptoModel>) {
        collection.clear()
        collection.addAll(models)
    }

    private fun updateCollectionView() {
        ultimateAdapter.notifyDataSetChanged()
    }

    private inner class CryptsObserver : DisposableObserver<Collection<CryptoModel>>() {
        override fun onNext(t: Collection<CryptoModel>) {
            setCollection(t)
            updateCollectionView()
        }

        override fun onError(e: Throwable) {
            onObservableFailed(e, getString(R.string.userFriendly_errorMessage))
            isLoading.set(false)
        }

        override fun onComplete() {
            isLoading.set(false)
        }
    }
}