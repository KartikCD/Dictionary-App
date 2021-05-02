package com.kartikcd.wordsearch

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.kartikcd.wordsearch.data.util.Resource
import com.kartikcd.wordsearch.databinding.ActivityMainBinding
import com.kartikcd.wordsearch.presentation.adapter.DictionaryAdapter
import com.kartikcd.wordsearch.presentation.viewmodel.DictionaryViewModel
import com.kartikcd.wordsearch.presentation.viewmodel.DictionaryViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: DictionaryViewModelFactory

    @Inject
    lateinit var dictionaryAdapter: DictionaryAdapter

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: DictionaryViewModel
    private val TAG = "MainActivity"
    private var AUDIO_URL = ""
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)
            .get(DictionaryViewModel::class.java)

        initRecyclerView()
        viewMeanings("smile")

        binding.voiceButton.setOnClickListener {
            playAudio()
        }

        binding.editSearch.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s == null) {
                viewMeanings("smile")
            } else {
                Log.i(TAG, "onTextChanged: ${s.toString()}")
                if(s.toString().length == 0) {
                    viewMeanings("smile")
                } else {
                    MainScope().launch {
                        delay(1000)
                        viewMeanings(s.toString())
                    }
                }
            }
        }

    }

    private fun playAudio() {
        if (!AUDIO_URL.equals("")) {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            try {
                mediaPlayer.setDataSource(AUDIO_URL)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: Exception) {
                Toast.makeText(this, "Cannot play audio.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvMeaning.apply {
            adapter = dictionaryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressbar.visibility = View.GONE
    }

    private fun viewMeanings(searchQuery: String) {
        showProgressBar()
        viewModel.getSearchedWordMeaning(searchQuery)
        viewModel.dictionaryMeaning.observe(this, Observer {response ->

            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        binding.relLayout.visibility = View.VISIBLE
                        val dictionaryItem = it[0]
                        binding.searchText.text = dictionaryItem.word

                        if (dictionaryItem.phonetics.isNotEmpty() ) {
                            binding.apply {
                                pronounceText.text = dictionaryItem.phonetics[0].text
                                pronounceText.visibility = View.VISIBLE
                                voiceButton.visibility = View.VISIBLE
                                AUDIO_URL = dictionaryItem.phonetics[0].audio
                            }
                        } else {
                            binding.apply {
                                pronounceText.visibility = View.GONE
                                voiceButton.visibility = View.GONE
                            }
                        }

                        dictionaryAdapter.differ.submitList(dictionaryItem.meanings.toList())
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                    binding.relLayout.visibility = View.GONE
                }

                is Resource.Error -> {
                    hideProgressBar()
                    binding.relLayout.visibility = View.GONE
                    response.message?.let {
//                        Toast.makeText(this, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                }
            }

        })
    }
}