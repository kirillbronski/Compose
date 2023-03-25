package com.bronski.compose.data.repository

import androidx.compose.foundation.pager.PageSize
import com.bronski.compose.data.remote.dataSource.DataSource
import com.bronski.compose.data.remote.model.ListItem
import kotlinx.coroutines.delay

class Repository() {

    private val remoteDataSource = DataSource()

    suspend fun getItem(nextPage: Int, pageSize: Int) =
        remoteDataSource.getItems(nextPage, pageSize)

}