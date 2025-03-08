package com.example.jetpackcomposeapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapp.data.details.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository
) : ViewModel() {
    private val _details = MutableStateFlow("Loading...")
    val details: StateFlow<String> = _details

    fun fetchDetails(itemId: String) {
        viewModelScope.launch {
            val result = repository.getItemDetails(itemId)
            _details.value = result
        }
    }
}
