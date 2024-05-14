package com.example.ulavto

import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.ulavto.adapter.ItemParts
import com.example.ulavto.databinding.ActivityContentBinding
import com.example.ulavto.db.Item
import com.example.ulavto.db.MainDb

class ContentActivity : AppCompatActivity() {
    lateinit var binding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = MainDb.getDb(this)
        val item = intent.getSerializableExtra("item") as ItemParts
        var isfav = item.favorite
        var iscart = item.cart
        var isDataHandledParts = true
        binding.apply {
            imageView5.setImageResource(item.avatarUrl)
            textView7.text = item.nameN
            textView10.text = item.cost.toString() + " ₽"
            textView12.text = item.description
            textView12.setMovementMethod(ScrollingMovementMethod())
            if (isfav == 0) {
                imageView2.setImageResource(R.drawable.favorite_button)
                favoritetextView.setText("   В избранное")
                favoritetextView.setTextColor(Color.argb(255, 76, 76, 77))
                favoritebutton.setBackgroundResource(R.drawable.button_bg)
            }
            else{
                imageView2.setImageResource(R.drawable.favorite_button_act)

                favoritetextView.setText("   В избранном")
                favoritetextView.setTextColor(Color.argb(255, 255, 255, 255))
                favoritebutton.setBackgroundResource(R.drawable.button_bg_act)
            }
            if (iscart == 0){
                carttextView.setText("В корзину")
                carttextView.setTextColor(Color.argb(255, 76, 76, 77))
                cartbuttoncontent.setBackgroundResource(R.drawable.button_bg)

            }
            else{

                carttextView.setText("В корзине")
                carttextView.setTextColor(Color.argb(255, 255, 255, 255))
                cartbuttoncontent.setBackgroundResource(R.drawable.button_bg_act)
                }

            favoritebutton.setOnClickListener{
                val itemp = Item(item.id,item.tag, item.nameN,item.description,item.cost, item.avatarUrl, 1, item.cart)
                val itemm = Item(item.id,item.tag, item.nameN,item.description,item.cost, item.avatarUrl, 0, item.cart)
                isDataHandledParts = true
                if (isfav == 0){
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                        if(isDataHandledParts){
                            Thread{
                                db.getDao().insertItem(itemp)
                            }.start()
                            imageView2.setImageResource(R.drawable.favorite_button_act)
                            favoritetextView.setText("   В избранном")
                            favoritetextView.setTextColor(Color.argb(255, 255, 255, 255))
                            favoritebutton.setBackgroundResource(R.drawable.button_bg_act)
                            isfav = 1
                        }
                        isDataHandledParts = false
                    }
                }
                else{
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                        if(isDataHandledParts) {
                            Thread {
                                db.getDao().insertItem(itemm)
                            }.start()
                            imageView2.setImageResource(R.drawable.favorite_button)
                            favoritetextView.setText("   В избранное")
                            favoritetextView.setTextColor(Color.argb(255, 76, 76, 77))
                            favoritebutton.setBackgroundResource(R.drawable.button_bg)
                            isfav = 0
                        }
                        isDataHandledParts = false
                    }
                }
            }

            cartbuttoncontent.setOnClickListener{
                val itemcp = Item(item.id,item.tag, item.nameN,item.description,item.cost, item.avatarUrl, item.favorite, 1)
                val itemcm = Item(item.id,item.tag, item.nameN,item.description,item.cost, item.avatarUrl, item.favorite, 0)
                isDataHandledParts = true
                if (iscart == 0){
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                        if(isDataHandledParts) {
                            Thread{
                                db.getDao().insertItem(itemcp)
                            }.start()

                            carttextView.setText("В корзине")
                            carttextView.setTextColor(Color.argb(255, 255, 255, 255))
                            cartbuttoncontent.setBackgroundResource(R.drawable.button_bg_act)
                            iscart = 1
                        }
                        isDataHandledParts = false
                    }
                }
                else {
                    db.getDao().getAllItems().asLiveData().observe(this@ContentActivity) {
                        if(isDataHandledParts) {
                            Thread {
                                db.getDao().insertItem(itemcm)
                            }.start()

                            carttextView.setText("В корзину")
                            carttextView.setTextColor(Color.argb(255, 76, 76, 77))
                            cartbuttoncontent.setBackgroundResource(R.drawable.button_bg)
                            iscart = 0
                        }
                    isDataHandledParts = false
                    }
                }
            }
            imageView6.setOnClickListener {
                finish()
            }
        }
    }
}

