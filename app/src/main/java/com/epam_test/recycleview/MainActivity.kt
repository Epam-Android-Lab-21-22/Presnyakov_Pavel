package com.epam_test.recycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.epam_test.recycleview.adapter.ExampleAdapter
import com.epam_test.recycleview.data.ExampleItem
import com.epam_test.recycleview.databinding.ActivityMainBinding
import com.epam_test.recycleview.databinding.ThirdLayoutBinding
import java.lang.Math.random
import kotlin.random.Random

class MainActivity : AppCompatActivity() {



    private lateinit var binding : ActivityMainBinding
    private lateinit var exampleAdapter: ExampleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            exampleAdapter = ExampleAdapter()
            adapter = exampleAdapter

        }
        exampleAdapter.updateItems(getItems())

        binding.secondActivityButton.setOnClickListener{
            val intent = Intent(this, SecondAcivity::class.java)
            startActivity(intent)
        }
    }

    private fun getItems(): List<ExampleItem>{
        return MutableList(INIT_LIST_SIZE){
            when(Random.nextInt(1,4)){
                1 -> ExampleItem.FirstItem(
                    number = it,
                    name = "Пример строки №")
                2 -> ExampleItem.SecondItem
                else -> {ExampleItem.ThirdItem}
            }
        }
        }

    companion object{
        const val INIT_LIST_SIZE: Int = 50
    }

}