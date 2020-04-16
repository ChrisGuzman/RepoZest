import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.chris_guzman.repozest.network.GitHubApi
import com.chris_guzman.repozest.network.GitHubApiClient
import com.chris_guzman.repozest.ui.organizations.OrgListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock


@RunWith(JUnit4::class)
class OrgListViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var gitHubApi: GitHubApi

    @Mock
    lateinit var gitHubApiClient: GitHubApiClient

    @Mock
    lateinit var observer: Observer<OrgListViewModel>

    @Before
    fun setup() {

    }


}