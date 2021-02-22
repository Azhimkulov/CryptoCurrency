package us.azhimkulov.cryptocurrency.view.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import us.azhimkulov.cryptocurrency.helper.text_changed_delay.factory.DelayTextChangeListenerFactory
import us.azhimkulov.cryptocurrency.view.viewmodel.CryptoCollectionViewModel
import us.azhimkulov.domain.interactor.GetCrypts
import javax.inject.Inject


/**
 * Created by azamat  on 2/21/21.
 */
class CryptoCollectionViewModelFactory @Inject constructor(
    private val getCrypts: GetCrypts,
    private val delayTextChangeListenerFactory: DelayTextChangeListenerFactory
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CryptoCollectionViewModel(getCrypts, delayTextChangeListenerFactory) as T
    }
}