package com.bronski.compose.domain.useCases

import com.bronski.compose.data.repository.Repository

class GetItemUseCase() {

    private val repository = Repository()

    suspend fun execute(nextPage: Int, pageSize: Int) = repository.getItem(nextPage, pageSize)

}