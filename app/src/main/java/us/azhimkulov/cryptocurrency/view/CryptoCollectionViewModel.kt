package us.azhimkulov.cryptocurrency.view

import android.util.Log
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
) : UltimateAdapter.UltimateAdapterDataSource {
    val ultimateAdapter = UltimateAdapter.newInstance()
    var isLoading: ObservableField<Boolean> = ObservableField(false)

    private val collection = mutableListOf<CryptoModel>()

    fun onViewCreated() {
        setupAdapter()
    }

    fun onResume() {
        isLoading.set(true)
        getCrypts.execute(CryptsObserver())
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

    private inner class CryptsObserver : DisposableObserver<Collection<CryptoModel>>() {
        override fun onNext(t: Collection<CryptoModel>) {
            Log.d("CRYPTO_COLLECTION", "onNext")
            collection.clear()
            collection.addAll(t)
            ultimateAdapter.notifyDataSetChanged()
        }

        override fun onError(e: Throwable) {
            Log.d("CRYPTO_COLLECTION", e.message ?: "empty")
            isLoading.set(false)
        }

        override fun onComplete() {
            Log.d("CRYPTO_COLLECTION", "onComplete")
            isLoading.set(false)
        }
    }
}