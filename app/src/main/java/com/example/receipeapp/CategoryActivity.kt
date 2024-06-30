package com.example.receipeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.receipeapp.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Receipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = intent.getStringExtra("TITLE")


        setupRecyclerView()
        binding.next.setOnClickListener{
            finish()
        }

    }

    private fun setupRecyclerView() {
        dataList = ArrayList()
        binding.categoryRv.layoutManager = LinearLayoutManager(this)

        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries().fallbackToDestructiveMigration().createFromAsset("recipe.db").build()


        var daoObject = db.getDao()
        var recipes = daoObject.getAll()

        for (i in recipes!!.indices){

            if(recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)){
                recipes[i]?.let { dataList.add(it) }
            }

            rvAdapter = CategoryAdapter(dataList, this)
            binding.categoryRv.adapter = rvAdapter
        }








    }
}