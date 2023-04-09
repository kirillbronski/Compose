package com.bronski.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bronski.compose.data.local.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import net.datafaker.Faker
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val faker: Faker,
) : ViewModel() {

    private val personList = (1..20).map {
        Person(
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
        )
    }

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(personList)

    @OptIn(FlowPreview::class)
    val persons = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_persons) { text, persons ->
            if (text.isBlank()) {
                persons
            } else {
                delay(2000)
                persons.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}



















