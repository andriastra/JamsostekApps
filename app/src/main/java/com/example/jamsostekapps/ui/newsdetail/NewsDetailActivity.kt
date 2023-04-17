package com.example.jamsostekapps.ui.newsdetail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jamsostekapps.data.remote.Article
import com.example.jamsostekapps.databinding.ActivityMainBinding
import com.example.jamsostekapps.databinding.ActivityNewsDetailBinding

class NewsDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        article = intent.getParcelableExtra("data")

        setupView()
    }

    fun setupView() {
        article?.let { article ->
            article.urlToImage?.let {
                Glide.with(this).load(it).into(binding.imgHeader)
            }
            article.title?.let{
                binding.titleNews.text = it
            }
            article.content?.let {
                binding.tvContent.text = it
            }
            article.publishedAt.let {
                binding.tvDate.text = it
            }
        }
    }
}