package com.example.ulavto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ulavto.adapter.ItemParts
import com.example.ulavto.adapter.PartsAdapter
import com.example.ulavto.databinding.ActivityMainBinding
import com.example.ulavto.db.Item
import com.example.ulavto.db.MainDb
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


        val db = MainDb.getDb(this)
        db.getDao().getAllItems().asLiveData().observe(this) {


            if (it.isNullOrEmpty()) {

                val sharedPref = getSharedPreferences("myPrefs", MODE_PRIVATE)

                val imageResourceNameEngine =
                    sharedPref.getString("image_resource_name", "logo_eng")
                val imageResourceIdEngine =
                    getResources().getIdentifier(imageResourceNameEngine, "drawable", packageName)

                val imageResourceNameBat =
                    sharedPref.getString("image_resource_name", "logo_bat")
                val imageResourceIdBat =
                    getResources().getIdentifier(imageResourceNameBat, "drawable", packageName)

                val imageResourceNameBrake =
                    sharedPref.getString("image_resource_name", "logo_brake")
                val imageResourceIdBrake =
                    getResources().getIdentifier(imageResourceNameBrake, "drawable", packageName)

                val imageResourceNameSup =
                    sharedPref.getString("image_resource_name", "logo_sup")
                val imageResourceIdSup =
                    getResources().getIdentifier(imageResourceNameSup, "drawable", packageName)

                val imageResourceNameOther =
                    sharedPref.getString("image_resource_name", "logo_other")
                val imageResourceIdOther =
                    getResources().getIdentifier(imageResourceNameOther, "drawable", packageName)

                val engineI4 = Item(
                    null,
                    "Двигатель","Двигатель ВАЗ-11183",
                    "Объем двигателя, куб.см\t1596\n" +
                            "\n" +
                            "Максимальная мощность, л.с.\t80 - 84\n" +
                            "\n" +
                            "Максимальный крутящий момент, Н*м (кг*м) при об./мин.\t132 (13) / 3800\n" +
                            "\n" +
                            "Тип двигателя\tРядный, 4-цилиндровый\n"+
                            "\n" +
                            "Расход топлива, л/100 км\t7.8\n"+
                            "\n"+
                            "Количество клапанов на цилиндр\t2\n", 130000,
                    imageResourceIdEngine,
                    0,0
                )

                val enginePlugs = Item(
                    null,
                    "Двигатель","Свечи зажигания NGK №13 BPR6ES-11",
                    "Колличество в упаковке\t4\n" +
                            "\n" +
                            "Число боковых электродов\t1\n" +
                            "\n" +
                            "Зазор между электродами, мм\t1.1\n" +
                            "\n" +
                            "Ширина зева гаечного ключа, мм\t21\n"+
                            "\n" +
                            "Страна-изготовитель\tФранция\n", 980,
                    imageResourceIdEngine,
                    0,0
                )

                val batTyumen = Item(
                    null,
                    "Аккумулятор","Автомобильный аккумулятор Tyumen Battery Standard 60 Ач",
                    "Напряжение\t12 В\n" +
                            "\n" +
                            "Пусковой ток\t550 А\n" +
                            "\n" +
                            "Емкость\t60 Ач\n" +
                            "\n" +
                            "Полярность\tпрямая\n"+
                            "\n" +
                            "Технология\tКальциевый\n"+
                            "\n"+
                            "Тип клеммы\tКонус стандартный (EU)\n"+
                            "\n"+
                            "Типоразмер DIN/JIS\tL2\n"+
                            "\n"+
                            "Длина\t242мм\n"+
                            "\n"+
                            "Высота\t190мм\n"+
                            "\n"+
                            "Ширина\t175мм\n", 5600,
                    imageResourceIdBat,
                    0,0
                )

                val batBrest = Item(
                    null,
                    "Аккумулятор","Автомобильный аккумулятор Brest Battery 60 Ач",
                    "Напряжение\t12 В\n" +
                            "\n" +
                            "Пусковой ток\t590 А\n" +
                            "\n" +
                            "Емкость\t60 Ач\n" +
                            "\n" +
                            "Полярность\tобратная\n"+
                            "\n" +
                            "Технология\tКальциевый\n"+
                            "\n"+
                            "Тип клеммы\tКонус стандартный (EU)\n"+
                            "\n"+
                            "Типоразмер DIN/JIS\tL2\n"+
                            "\n"+
                            "Длина\t242мм\n"+
                            "\n"+
                            "Высота\t190мм\n"+
                            "\n"+
                            "Ширина\t175мм\n", 5990,
                    imageResourceIdBat,
                    0,0
                )

                val brakeDisk = Item(
                    null,
                    "Тормозная система","Тормозной диск передний Hofer 130 255",
                    "Тип тормозного диска\tвентилируемый, с перфорацией\n" +
                            "\n" +
                            "Размер тормозного диска\t255 мм\n" +
                            "\n" +
                            "Колличество\t2 шт\n" +
                            "\n" +
                            "Мост установки\tпередний\n"+
                            "\n" +
                            "Модель автомобиля\tLADA Granta, LADA Kalina\n", 5300,
                    imageResourceIdBrake,
                    0,0
                )

                val brakePads = Item(
                    null,
                    "Тормозная система","Комплект тормозных колодок-LADA SPORT",
                            "Колличество\t4 шт\n" +
                            "\n" +
                            "Мост установки\tпередний\n"+
                            "\n" +
                            "Модель автомобиля\tLADA Granta, LADA Kalina\n", 1200,
                    imageResourceIdBrake,
                    0,0
                )

                val supRack = Item(
                    null,
                    "Подвеска","Рулевой механизм LADA 1118 с тягами",
                    "Колличество оборотов\t3.1 оборота\n" +
                            "\n" +
                            "Модель автомобиля\tLADA Granta, LADA Kalina\n", 12900,
                    imageResourceIdSup,
                    0,0
                )

                val supSpringRack = Item(
                    null,
                    "Подвеска","Амортизатор (cтойка) передней подвески",
                    "Рабочая среда\tмасляный\n" +
                            "\n" +
                            "Мост установки\tпередний левый\n"+
                            "\n" +
                            "Колличество\t1 шт\n" +
                            "\n" +
                            "Модель автомобиля\tLADA Granta, LADA Kalina\n", 2250,
                    imageResourceIdSup,
                    0,0
                )

                val otherLight = Item(
                    null,
                    "Остальное","Лампа автомобильная галогенная Philips Vision",
                    "Типоразмер\tH4\n" +
                            "\n" +
                            "Свет\tтеплый белый\n"+
                            "\n" +
                            "Колличество\t1 шт\n" +
                            "\n" +
                            "Мощность\t60 Вт\n" +
                            "\n" +
                            "Напряжение\t12 В\n" +
                            "\n" +
                            "Цветовая температура\t3200 K\n", 305,
                    imageResourceIdOther,
                    0,0
                )

                val otherOil = Item(
                    null,
                    "Остальное","Масло моторное LUKOIL LUXE",
                    "Объем\t4 л\n" +
                            "\n" +
                            "Класс вязкости SAE\t10W-40\n"+
                            "\n" +
                            "Стандарт API\tSL/CF\n" +
                            "\n" +
                            "Тип масла\tсинтетика\n", 1165,
                    imageResourceIdOther,
                    0,0
                )

                Thread {
                    db.getDao().insertItem(engineI4)
                    db.getDao().insertItem(enginePlugs)
                    db.getDao().insertItem(batTyumen)
                    db.getDao().insertItem(batBrest)
                    db.getDao().insertItem(brakeDisk)
                    db.getDao().insertItem(brakePads)
                    db.getDao().insertItem(supRack)
                    db.getDao().insertItem(supSpringRack)
                    db.getDao().insertItem(otherLight)
                    db.getDao().insertItem(otherOil)
                }.start()


            }
        }
    }
}