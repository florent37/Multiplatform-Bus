package sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val mainPresenter by lazy { dependencies.mainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter.bind(this)
        mainPresenter.start()

        premiumButton.setOnClickListener {
            mainPresenter.becomePremium()
        }
    }

    override fun onDestroy() {
        mainPresenter.unbind()
        super.onDestroy()
    }

    override fun displayUserStatus(status: String) {
        userStatus.text = status
    }


}
