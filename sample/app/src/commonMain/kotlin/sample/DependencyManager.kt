package sample

import com.github.florent37.bus.Bus

class DependencyManager {

    val bus by lazy { Bus.getDefault() }

    val premiumManager by lazy { PremiumManager(bus) }

    fun mainPresenter() = MainPresenter(premiumManager, bus)

}
