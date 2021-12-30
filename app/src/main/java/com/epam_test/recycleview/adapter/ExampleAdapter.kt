package com.epam_test.recycleview.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.epam_test.recycleview.R
import com.epam_test.recycleview.data.DiffUtilCallback
import com.epam_test.recycleview.data.ExampleItem
import com.epam_test.recycleview.databinding.FirstLayoutBinding
import com.epam_test.recycleview.databinding.SecondLayoutBinding
import com.epam_test.recycleview.databinding.ThirdLayoutBinding
import java.lang.Exception

class ExampleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<ExampleItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.FIRST_ITEM_TYPE.ordinal -> {
                FirstViewHolder(
                    FirstLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }
        ViewType.SECOND_ITEM_TYPE.ordinal -> {
            SecondViewHolder(
                SecondLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
            ViewType.THIRD_ITEM_TYPE.ordinal -> {
                ThirdViewHolder(
                    ThirdLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw  Exception("viewType не известен")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FirstViewHolder -> {holder.bind(items[position] as ExampleItem.FirstItem )}
            is SecondViewHolder ->{}
            is ThirdViewHolder ->{
                holder.itemView.findViewById<Button>(R.id.delete_button).setOnClickListener {
                    it.isClickable = false
                    deleteItem(holder)}
            }
        }
    }

    private fun deleteItem(holder: ThirdViewHolder) {
        items.removeAt(holder.adapterPosition)
        notifyItemRemoved(holder.adapterPosition)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is ExampleItem.FirstItem -> ViewType.FIRST_ITEM_TYPE.ordinal
            is ExampleItem.SecondItem -> ViewType.SECOND_ITEM_TYPE.ordinal
            is ExampleItem.ThirdItem -> ViewType.THIRD_ITEM_TYPE.ordinal
            is ExampleItem.GridItem-> ViewType.SECOND_ITEM_TYPE.ordinal
        }
    }


    fun updateItems(items: List<ExampleItem>) {
        val result = DiffUtil.calculateDiff(
           DiffUtilCallback(
                oldItems = this.items,
                newItems = items
            )
        )
        this.items.clear()
        this.items.addAll(items)
       result.dispatchUpdatesTo(this)
    }

    class FirstViewHolder(
        private val viewBinding: FirstLayoutBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: ExampleItem.FirstItem) {
            viewBinding.apply {
                name.text = item.name
                number.text = item.number.toString()
            }

        }}

        class SecondViewHolder(
            private val viewBinding: SecondLayoutBinding
        ) : RecyclerView.ViewHolder(viewBinding.root) {
        }

    class ThirdViewHolder(
        private val viewBinding: ThirdLayoutBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

    }

    enum class ViewType{
        FIRST_ITEM_TYPE,
        SECOND_ITEM_TYPE,
        THIRD_ITEM_TYPE
    }
}