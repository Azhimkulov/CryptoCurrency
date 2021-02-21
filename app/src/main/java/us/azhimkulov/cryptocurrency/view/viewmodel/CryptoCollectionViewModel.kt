package us.azhimkulov.cryptocurrency.view.viewmodel

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
            collection.clear()
            collection.addAll(t)
            ultimateAdapter.notifyDataSetChanged()
        }

        override fun onError(e: Throwable) {
            Log.d("OBSERVABLE_FAILED", e.message ?: "Throwable message is empty")
            isLoading.set(false)
        }

        override fun onComplete() {
            isLoading.set(false)
        }
    }
}