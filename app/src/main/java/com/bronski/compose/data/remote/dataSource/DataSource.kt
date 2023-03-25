package com.bronski.compose.data.remote.dataSource

import com.bronski.compose.data.remote.model.ListItem
import com.bronski.compose.data.repository.Repository
import kotlinx.coroutines.delay

class DataSource{

    private val remoteDataSource = (1..100).map {
        ListItem(
            title = "Item $it",
            description = "Description $it"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<ListItem>> {
        delay(2000)
        val startingIndex = page * pageSize
        return if (startingIndex + pageSize <= remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }

}