package com.example.receipeapp

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.receipeapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList: ArrayList<Receipe>
    private lateinit var recipes: List<Receipe?>

    @SuppressLint("ServiceCast", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.search.requestFocus()

        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries().fallbackToDestructiveMigration().createFromAsset("recipe.db").build()
        var daoObject = db.getDao()
        recipes = daoObject.getAll()!!

        setupRecyclerView()
        binding.gobck.setOnClickListener{

            finish()
        }
        binding.search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(s.toString() != "")
                {
                    filterData(s.toString())
                }
                else
                {

                    setupRecyclerView()
                }
            }


            override fun afterTextChanged(s: Editable?) {

            }

        })


//        var imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodService
//        binding.rvsearch.setOnTouchListener{v, event ->
//            imm.hideStatusIcon()
//
//        }







    }

    private fun filterData(filterText: String) {
        var filterData =  ArrayList<Receipe>()

        for (i in recipes.indices){
            if(recipes[i]!!.tittle.toLowerCase().contains(filterText.lowercase()))
            {
               filterData.add(recipes[i]!!)
            }

            rvAdapter.filterList(filterList = filterData)
        }



    }

    private fun setupRecyclerView() {
        dataList = ArrayList()
        binding.rvsearch.layoutManager = LinearLayoutManager(this)


        for (i in recipes!!.indices){

            if(recipes[i]!!.category.contains("Popular")){
                recipes[i]?.let { dataList.add(it) }
            }

            rvAdapter = SearchAdapter(dataList, this)
            binding.rvsearch.adapter = rvAdapter
        }








    }

}