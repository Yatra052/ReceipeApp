package com.example.receipeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.receipeapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Receipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()
        binding.search.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }

        binding.salad.setOnClickListener{
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
             intent.putExtra("TITLE", "Salad")
            intent.putExtra("CATEGORY", "Salad")
            startActivity(intent)
        }

        binding.MainDish.setOnClickListener{
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("TITLE", "Main Dish")
            intent.putExtra("CATEGORY", "Dish")
            startActivity(intent)
        }

        binding.Drinks.setOnClickListener{
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("TITLE", "Drinks")
            intent.putExtra("CATEGORY", "Drinks")
            startActivity(intent)
        }

        binding.dessert.setOnClickListener{
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("TITLE", "Desserts")
            intent.putExtra("CATEGORY", "Desserts")
            startActivity(intent)
        }

    }

    private fun setupRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var db = Room.databaseBuilder(this@HomeActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries().fallbackToDestructiveMigration().createFromAsset("recipe.db").build()


        var daoObject = db.getDao()
        var recipes = daoObject.getAll()

        for (i in recipes!!.indices){

            if(recipes[i]!!.category.contains("Popular")){
                recipes[i]?.let { dataList.add(it) }
            }

            rvAdapter = PopularAdapter(dataList, this)
            binding.rvPopular.adapter = rvAdapter
        }








    }
}