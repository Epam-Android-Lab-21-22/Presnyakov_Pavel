package com.epam_test.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epam_test.recycleview.adapter.SecondAdapter
import com.epam_test.recycleview.data.ExampleItem
import com.epam_test.recycleview.databinding.ActivitySecondAcivityBinding
import kotlin.random.Random

class SecondAcivity : AppCompatActivity() {


    private lateinit var binding : ActivitySecondAcivityBinding
    private lateinit var secondAdapter: SecondAdapter
    private val itemsViewModel by lazy {
        ViewModelProviders.of(this).get(GridViewModel::class.java)
    }
    private val BASE_GRID_COUNT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.secondMainRecycleView.apply {
            initRecyclerView()
        }

        binding.addButton.setOnClickListener{
            secondAdapter.addItem()
        }

    }


    private fun RecyclerView.initRecyclerView() {
        layoutManager = GridLayoutManager(context, 2)
        secondAdapter = SecondAdapter(itemsViewModel)
        adapter = secondAdapter
    }

    private fun getItems(): List<ExampleItem>{
        return MutableList(INIT_LIST_SIZE){
            when(Random.nextInt(1,4)){
                1 -> ExampleItem.FirstItem(
                    number = it,
                    name = "Иван Иванов")
                2 -> ExampleItem.SecondItem
                else -> {
                    ExampleItem.ThirdItem}
            }
        }
    }

    companion object{
        private  const val INIT_LIST_SIZE: Int = 50
    }

}