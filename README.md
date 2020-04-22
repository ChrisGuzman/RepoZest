# RepoZest

Search for GitHub organizations and their most popular repos.

## Building the app
This project uses the Gradle build system. To build this project, use the gradlew build command or use "Import Project" in Android Studio.

# Using the app
Search for the organization on github you're looking for in the search box. As you type, results matching your search will appear.
Click on the organization to see the the three most popular repositories by stars.
Click on a repository to navigate the codebase in a Chrome Custom Tab.

## Testing the app
There are viewmodel tests in `OrgListViewModelTest` and `RepoListViewModelTest` the tests can be ran from those files or you can run the tests from the command line with `$ ./gradlew test`