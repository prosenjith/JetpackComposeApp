package com.example.jetpackcomposeapp.asynchronous

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PostViewModel : ViewModel() {

    private val repository = PostRepository()
    private val _postFlow = MutableStateFlow<Post?>(null)
    val postFlow: StateFlow<Post?> = _postFlow

    private val handler = Handler(Looper.getMainLooper())

    fun loadPost() {
        Thread {
            val result = repository.fetchPostSync()

            handler.post {
                _postFlow.value = result
            }
        }.start()
    }
}
