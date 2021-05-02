package com.kartikcd.wordsearch.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kartikcd.wordsearch.data.model.Meaning
import com.kartikcd.wordsearch.databinding.ListMeaningsBinding
import org.json.JSONObject

class DictionaryAdapter: RecyclerView.Adapter<DictionaryAdapter.MyViewHolder>() {

    private val TAG = "DictionaryAdapter"

    private val callback = object : DiffUtil.ItemCallback<Meaning>() {
        override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem.partOfSpeech == newItem.partOfSpeech
        }

        override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListMeaningsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val meaning = differ.currentList[position]
        holder.bind(meaning)
    }

    inner class MyViewHolder(
        val binding: ListMeaningsBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(meaning: Meaning) {
            binding.grammerText.text = meaning.partOfSpeech
            val definition = meaning.definitions[0]
            binding.meaningText.text = definition.definition

            val jsonString = Gson().toJson(definition)
            Log.i(TAG, "bind: ${jsonString}")

            val containerObject = JSONObject(jsonString)

            if (containerObject.has("synonyms")) {
                binding.synonymText.visibility = View.VISIBLE
                binding.synonymwordText.visibility = View.VISIBLE
                var message = ""
                for (i in 0..definition.synonyms.size - 1) {
                    if (i == definition.synonyms.size - 1) {
                        message += "\u25CF ${definition.synonyms[i]}"
                    } else {
                        message += "\u25CF ${definition.synonyms[i]} \n"
                    }
                }
//                definition.synonyms.forEach {
//                    message += "\u25CF ${it}\n"
//                }
                binding.synonymwordText.text = message
            } else {
                binding.synonymText.visibility = View.GONE
                binding.synonymwordText.visibility = View.GONE
            }

            if (containerObject.has("example")) {
                binding.exampleText.visibility = View.VISIBLE
                binding.examplewordText.visibility = View.VISIBLE
                binding.examplewordText.text = definition.example
            } else {
                binding.exampleText.visibility = View.GONE
                binding.examplewordText.visibility = View.GONE
            }

        }

    }

}