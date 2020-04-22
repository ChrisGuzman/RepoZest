import com.chris_guzman.repozest.model.GitHubResponse
import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.ui.BaseListViewModelTest
import com.chris_guzman.repozest.ui.organizations.viewmodel.OrgListViewModel
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mockito.`when`


@RunWith(JUnit4::class)
class OrgListViewModelTest: BaseListViewModelTest() {

    @InjectMocks
    var orgListViewModel =
        OrgListViewModel()

    private var testObservable: Observable<GitHubResponse<Organization>>? = null

    @Test
    fun `when viewmodel calls loadOrgs then list of organizations is returned`() {
        val org = Organization(id = 42, login = "NYTimes", avatar_url = "foo.bar", repos_url = "fizz.buzz")
        val orgList = listOf(org)
        val response = GitHubResponse(42, orgList)

        testObservable = Observable.just(response)

        `when`(gitHubApiClient.getOrganizations()).thenReturn(testObservable)
        orgListViewModel.loadOrgs()

        assertEquals(1, orgListViewModel.data.value?.size)
    }

    @Test
    fun `when a networking error occurs then the errorMessage is present`() {
        testObservable = Observable.error(Throwable())

        `when`(gitHubApiClient.getOrganizations()).thenReturn(testObservable)

        orgListViewModel.loadOrgs()

        assertNotNull(orgListViewModel.errorMessage.value)

    }


}