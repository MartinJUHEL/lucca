package com.martin.lucca.feature.thrombinoscope.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.ui.component.CenteredCircularProgressIndicator
import com.martin.lucca.core.ui.component.GenericErrorScreen
import com.martin.lucca.core.ui.theme.MarginRegular
import com.martin.lucca.core.ui.theme.MarginSmall
import com.martin.lucca.core.ui.theme.Typography
import com.martin.lucca.feature.thrombinoscope.R
import com.martin.lucca.feature.thrombinoscope.presentation.component.EmployeeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ThrombinoscopeScreen(
    action: (ThrombinoscopeAction) -> Unit,
    uiState: ThrombinoscopeUiState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.thrombinoscopeTitle),
                    style = Typography.titleMedium
                )
            })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState) {
                ThrombinoscopeUiState.Loading -> CenteredCircularProgressIndicator()
                ThrombinoscopeUiState.Error -> GenericErrorScreen(onRetry = {
                    action(ThrombinoscopeAction.Load)
                })

                is ThrombinoscopeUiState.Success -> EmployeesList(
                    uiState.employees,
                    onUserClick = { userId -> action(ThrombinoscopeAction.OnUserClicked(userId)) },
                    onPullToRefresh = { action(ThrombinoscopeAction.OnPullToRefresh) },
                    isRefreshing = uiState.isRefreshing
                )

                ThrombinoscopeUiState.Empty -> EmptyScreen()
            }
        }
    }
}

@Composable
private fun EmptyScreen() {
    return Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.thrombinoscopeTitle), style = Typography.displayLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmployeesList(
    employees: List<Employee>,
    onUserClick: (Int) -> Unit,
    onPullToRefresh: () -> Unit,
    isRefreshing: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "${employees.size} Collaborateurs",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(horizontal = MarginRegular)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(MarginSmall))

        // Grid of employees
        PullToRefreshBox(
            onRefresh = onPullToRefresh,
            isRefreshing = isRefreshing
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                contentPadding = PaddingValues(MarginRegular),
                horizontalArrangement = Arrangement.spacedBy(MarginRegular),
                verticalArrangement = Arrangement.spacedBy(MarginRegular)
            ) {
                items(employees) { user ->
                    EmployeeCard(employee = user, onClick = { onUserClick(user.id) })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ThrombinoscopeScreenPreview() {
    MaterialTheme {
        ThrombinoscopeScreen(
            action = {},
            uiState = ThrombinoscopeUiState.Success(
                employees = listOf(
                    Employee(
                        id = 1,
                        name = "Martin Juhel",
                        firstName = "Martin",
                        lastName = "Juhel",
                        jobTitle = "Android Developer",
                        pictureName = "profile_picture.jpg",
                        pictureUrl = null
                    ),
                    Employee(
                        id = 1,
                        name = "Martin Juhel",
                        firstName = "Martin",
                        lastName = "Juhel",
                        jobTitle = "Android Developer",
                        pictureName = "profile_picture.jpg",
                        pictureUrl = null
                    ),
                    Employee(
                        id = 1,
                        name = "Martin Juhel",
                        firstName = "Martin",
                        lastName = "Juhel",
                        jobTitle = "Android Developer",
                        pictureName = "profile_picture.jpg",
                        pictureUrl = null
                    ),
                )
            )
        )
    }
}