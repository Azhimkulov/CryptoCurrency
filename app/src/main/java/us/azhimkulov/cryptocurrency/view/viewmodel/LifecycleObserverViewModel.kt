package us.azhimkulov.cryptocurrency.view.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel

/**
 * Created by azamat  on 2/21/21.
 */
abstract class LifecycleObserverViewModel : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        //intentionally left empty
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        //intentionally left empty
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        //intentionally left empty
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        //intentionally left empty
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        //intentionally left empty
    }
}