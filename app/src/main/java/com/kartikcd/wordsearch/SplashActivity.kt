package com.kartikcd.wordsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.kartikcd.wordsearch.databinding.ActivitySplashBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainScope().launch {
            val animationRotate = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.rotate)
            binding.imageview.startAnimation(animationRotate)
            delay(1000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

    }
}