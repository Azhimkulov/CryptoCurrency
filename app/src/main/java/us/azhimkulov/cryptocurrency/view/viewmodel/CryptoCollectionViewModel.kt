package us.azhimkulov.cryptocurrency.view.viewmodel

import androidx.databinding.ObservableField
import io.reactivex.observers.DisposableObserver
import us.azhimkulov.cryptocurrency.R
import us.azhimkulov.cryptocurrency.helper.text_changed_delay.DelayTextChangeListener
import us.azhimkulov.cryptocurrency.helper.text_changed_delay.factory.DelayTextChangeListenerFactory
import us.azhimkulov.cryptocurrency.view.adapter.UltimateAdapter
import us.azhimkulov.domain.interactor.GetCrypts
import us.azhimkulov.domain.model.CryptoModel
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class CryptoCollectionViewModel @Inject constructor(
    private val getCrypts: GetCrypts,
    delayTextChangeListenerFactory: DelayTextChangeListenerFactory
) : LoadingViewModel(), UltimateAdapter.UltimateAdapterDataSource {

    val ultimateAdapter = UltimateAdapter.newInstance()
    var isLoading: ObservableField<Boolean> = ObservableField(false)
    val afterTextChanged: (String?) -> Unit = { query ->
        queryDelayTextChangeListener.textChanged {
            getCrypts.execute(CryptsObserver(), query)
        }
    }

    private var isInitialize = false
    private val collection = mutableListOf<CryptoModel>()
    private val queryDelayTextChangeListener: DelayTextChangeListener

    init {
        setupAdapter()
        queryDelayTextChangeListener = delayTextChangeListenerFactory.create()
    }

    override fun onResume() {
        super.onResume()
        if (!isInitialize) {
            isInitialize = true
            isLoading.set(true)
            getCrypts.execute(CryptsObserver())
        }
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