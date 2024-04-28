package com.example.ulavto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ulavto.databinding.ActivityMainBinding
import com.example.ulavto.fragments.AboutFragment
import com.example.ulavto.fragments.CartFragment
import com.example.ulavto.fragments.FavoriteFragment
import com.example.ulavto.fragments.MenuFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.menuButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, MenuFragment.newInstance()).commit()
        }

        binding.cartButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, CartFragment.newInstance()).commit()
        }

        binding.favoriteButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, FavoriteFragment.newInstance()).commit()
        }

        binding.aboutButton.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.place_holder, AboutFragment.newInstance()).commit()
        }

        supportFragmentManager.beginTransaction().replace(R.id.place_holder, MenuFragment.newInstance()).commit()
    }
}