package sample

class DependencyManager {

    val bus by lazy { Bus.getDefault() }

    val premiumManager by lazy { PremiumManager(bus) }

    fun mainPresenter() = MainPresenter(premiumManager, bus)

}
