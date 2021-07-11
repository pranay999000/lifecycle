package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livedata.adapter.SynonymsAdapter
import com.example.livedata.adapter.VideoAdapter
import com.example.livedata.databinding.ActivityMainBinding
import com.example.livedata.model.server.Dictionary
import com.example.livedata.model.server.Vid
import com.example.livedata.repository.Repository
import com.example.livedata.viewModel.MainViewModel
import com.example.livedata.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.dictionary.observe(this, { res ->
            if (res.isSuccessful) {
                fillView(res.body())
            } else {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                Log.e("error_code", res.code().toString())
            }
        })

        viewModel.getDictionary()
    }

    private fun fillView(body: Dictionary?) {
        binding.videoHeader.text = body?.videos?.header
        binding.wordHeader.text = body?.wordDay?.header
        binding.word.text = body?.wordDay?.word
        binding.meaning.text = body?.wordDay?.generic

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager1 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val linearLayoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val videoAdapter = VideoAdapter(this, body?.videos?.vids as ArrayList<Vid>)
        val synonymsAdapter = SynonymsAdapter(this, body.wordDay.synonym as ArrayList<String>)
        val synonymsAdapter1 = SynonymsAdapter(this, body.wordDay.anotonyms as ArrayList<String>)

        binding.videoRecycler.layoutManager = linearLayoutManager
        binding.videoRecycler.setHasFixedSize(true)
        binding.videoRecycler.adapter = videoAdapter

        binding.synonymsRecycler.layoutManager = linearLayoutManager1
        binding.synonymsRecycler.setHasFixedSize(true)
        binding.synonymsRecycler.adapter = synonymsAdapter

        binding.antonymsRecycler.layoutManager = linearLayoutManager2
        binding.antonymsRecycler.setHasFixedSize(true)
        binding.antonymsRecycler.adapter = synonymsAdapter1
    }
}