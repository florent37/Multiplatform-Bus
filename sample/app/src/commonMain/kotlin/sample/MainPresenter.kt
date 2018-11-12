package sample

import com.github.florent37.log.Logger

interface MainView {
    fun displayUserStatus(status: String)
}

class MainPresenter(val premiumManager: PremiumManager, val bus: Bus) {

    private var view: MainView? = null
    private var premium = false

    private val TAG = "Main-Presenter"

    fun start() {
        this.premium = premiumManager.premium

        bus.addObserver<Boolean>(this, Messages.PREMIUM) {
            this.premium = it
            Logger.d(TAG, "premium: ${it}")
            displayPremium()
        }

        displayPremium()
    }

    private fun displayPremium(){
        if(premium) {
            view?.displayUserStatus("premium")
        } else {
            view?.displayUserStatus("not premium")
        }
    }

    fun becomePremium() {
        premiumManager.becomePremium()
    }

    fun bind(view: MainView){
        this.view = view

        bus.removeObserver<Any>(this)
    }
    fun unbind(){
        view = null
    }

}