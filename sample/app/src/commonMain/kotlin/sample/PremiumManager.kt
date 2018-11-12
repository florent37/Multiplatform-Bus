package sample

import com.github.florent37.bus.Bus
import com.github.florent37.log.Logger

class PremiumManager(private val bus: Bus) {

    private val TAG = "Main-PremiumManager"

    var premium = false
        private set(value) {
            field = value
            Logger.d(TAG, "premium = true")
            bus.post(Messages.PREMIUM, value)
        }

    fun becomePremium() {
        premium = true
    }

}