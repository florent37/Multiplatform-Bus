import UIKit
import app

class ViewController: UIViewController, MainView {

    lazy var presenter: MainPresenter = {
        dependencies().mainPresenter()
    }()
    
    @IBOutlet weak var userStatus: UILabel!
    @IBAction func becomePremiumClicked(_ sender: Any) {
        presenter.becomePremium()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.bind(view: self)
        presenter.start()
    }
    
    func displayUserStatus(status: String) {
        self.userStatus.text = status
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        presenter.unbind()
    }
    
}
