package us.azhimkulov.cryptocurrency.view.viewmodel

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import us.azhimkulov.cryptocurrency.R
import us.azhimkulov.cryptocurrency.model.ToastDuration

/**
 * Created by azamat  on 2/21/21.
 */
abstract class LoadingViewModel : LifecycleObserverViewModel() {

    lateinit var onToastShow: (String, ToastDuration) -> Unit
    lateinit var onAlertDialogShow: (AlertDialog) -> Unit
    lateinit var context: () -> Context?

    fun onObservableFailed(throwable: Throwable, defaultErrorMessage: String) {
        Log.d(
            getString(R.string.observableFailed_tag),
            throwable.message ?: getString(R.string.defaultThrowable_emptyMessage)
        )
        showToast(throwable.localizedMessage ?: defaultErrorMessage, ToastDuration.SHORT)
    }

    fun showToast(message: String, duration: ToastDuration) {
        onToastShow(message, duration)
    }

    fun showAlertDialog(alertDialog: AlertDialog) {
        onAlertDialogShow(alertDialog)
    }

    protected fun getString(resourceId: Int): String {
        return context()!!.getString(resourceId)
    }
}