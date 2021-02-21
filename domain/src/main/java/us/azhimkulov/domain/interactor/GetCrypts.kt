package us.azhimkulov.domain.interactor

import io.reactivex.Observable
import us.azhimkulov.domain.executor.PostExecutionThread
import us.azhimkulov.domain.executor.ThreadExecutor
import us.azhimkulov.domain.model.CryptoModel
import us.azhimkulov.domain.repository.CryptoRepository
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class GetCrypts @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) :
    UseCase<Collection<CryptoModel>, Void?>(
        threadExecutor,
        postExecutionThread
    ) {

    override fun buildUseCaseObservable(params: Void?): Observable<Collection<CryptoModel>> {
        return cryptoRepository.getCrypts()
    }
}