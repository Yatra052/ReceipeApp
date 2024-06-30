package com.example.receipeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import androidx.collection.intIntMapOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.receipeapp.databinding.ActivityReceipeBinding

class ReceipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceipeBinding
    var imgCrop = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.next.setOnClickListener {
            var intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }


        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
        binding.title.text = intent.getStringExtra("tittle")
//        binding.ingData.text = intent.getStringExtra("ing")
        binding.stepData.text = intent.getStringExtra("des")

        var ing  = intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
            ?.toTypedArray()
        binding.time.text = ing?.get(0)
        for (i in 1 until ing?.size!!)
        {
            binding.ingData.text =
                """${binding.ingData.text} 🟢 ${ing[i]}\n+""".trimIndent()
             }

        binding.step.background = null
             binding.step.setTextColor(getColor(R.color.black))
             binding.step.setOnClickListener{
            binding.step.setBackgroundColor(R.drawable.btn_ing)

            binding.step.setTextColor(getColor(R.color.white))
            binding.step.setTextColor(getColor(R.color.black))
            binding.ing.background = null

            binding.stepsScroll.visibility = View.VISIBLE

            binding.ingScroll.visibility = View.GONE
        }

        binding.ing.setOnClickListener{
            binding.ing.setBackgroundColor(R.drawable.btn_ing)

            binding.ing.setTextColor(getColor(R.color.white))
            binding.step.setTextColor(getColor(R.color.black))
            binding.step.background = null

            binding.ingScroll.visibility = View.VISIBLE

            binding.stepsScroll.visibility = View.GONE



        }
        binding.fullscreen.setOnClickListener{
           if (imgCrop)
           {

               binding.itemImg.scaleType = ImageView.ScaleType.FIT_CENTER
               Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
               binding.fullscreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
               binding.shade.visibility = View.GONE
               imgCrop =! imgCrop
           }
            else{
                binding.itemImg.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
                binding.fullscreen.setColorFilter(null)
                binding.shade.visibility = View.VISIBLE
                imgCrop =! imgCrop
           }

        }
    }
}