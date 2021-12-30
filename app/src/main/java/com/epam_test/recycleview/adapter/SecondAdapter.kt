package com.epam_test.recycleview.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.epam_test.recycleview.GridViewModel
import com.epam_test.recycleview.R
import com.epam_test.recycleview.data.DiffUtilCallback
import com.epam_test.recycleview.data.ExampleItem
import com.epam_test.recycleview.databinding.FirstLayoutBinding
import com.epam_test.recycleview.databinding.SecondLayoutBinding
import com.epam_test.recycleview.databinding.TableItemBinding
import com.epam_test.recycleview.databinding.ThirdLayoutBinding

class SecondAdapter(private val gridViewModel: GridViewModel): RecyclerView.Adapter<SecondAdapter.SecondViewHolder>() {


    class SecondViewHolder(viewBinding: TableItemBinding): RecyclerView.ViewHolder(viewBinding.root)
    var items = gridViewModel.countItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        return SecondViewHolder(
            TableItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
    }
    override fun getItemCount(): Int {
        return items
    }


    fun addItem(){
        gridViewModel.countItems++
        items++
        notifyItemInserted(items + 1)
    }

}