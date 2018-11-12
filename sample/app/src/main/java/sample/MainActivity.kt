package sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val mainPresenter by lazy { dependencies.mainPresenter() }
    private val bus by lazy { dependencies.bus }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter.bind(this)
        mainPresenter.start()

        premiumButton.setOnClickListener {
            mainPresenter.becomePremium()
        }

        bus.addObserver<Boolean>(this, Messages.PREMIUM){
            Log.d("MainActivity", "observer pinged : ${it}")
        }
    }

    override fun onDestroy() {
        mainPresenter.unbind()
        bus.removeObserver<Any>(this)
        super.onDestroy()
    }

    override fun displayUserStatus(status: String) {
        userStatus.text = status
    }


}
