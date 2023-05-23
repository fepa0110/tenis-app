import androidx.lifecycle.ViewModel
import com.example.tenisapp.data.AppContainer
import com.example.tenisapp.viewModel.LoginViewModel
import com.example.tenisapp.viewModel.TournamentsViewModel

class TenisViewModelProvider(private val dataContainer: AppContainer) {
    fun getTournamentsViewModel(): ViewModel =  TournamentsViewModel(dataContainer.tournamentsRepository)
    fun getLoginViewModel(): ViewModel =  LoginViewModel(dataContainer.usersRepository)
}