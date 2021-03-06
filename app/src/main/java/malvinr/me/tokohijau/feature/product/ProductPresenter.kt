package malvinr.me.tokohijau.feature.product

import io.reactivex.disposables.CompositeDisposable
import malvinr.me.tokohijau.data.DataManager

class ProductPresenter(private val dataManager: DataManager) {

    private var view: ProductView? = null
    private var disposables: CompositeDisposable? = null

    fun onAttach(view: ProductView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    fun onDetach() {
        view = null
        disposables?.clear()
    }

    fun searchProduct(param: ProductParam) {
        view?.onShowLoading()

        val disposable = dataManager
            .searchProduct(param)
            .subscribe({
                view?.onShowProduct(it)
            }, {
                view?.onShowErrorMessage(it.localizedMessage)
            })

        disposables?.add(disposable)

        view?.onHideLoading()
    }
}