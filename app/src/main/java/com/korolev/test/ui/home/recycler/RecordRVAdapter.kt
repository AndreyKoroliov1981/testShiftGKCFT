package com.korolev.test.ui.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.korolev.domain.home.model.Record
import com.korolev.test.databinding.ItemListRequestBinding

class RecordRVAdapter(
    private val onClickBasketListener: RVOnClickBasketListener
) : RecyclerView.Adapter<RecordRVAdapter.RecordRVViewHolder>() {
    private val asyncListDiffer = AsyncListDiffer(this, RecordRVDiffUtilCallback())

    fun updateList(list: List<Record>) {
        asyncListDiffer.submitList(list)
    }

    inner class RecordRVViewHolder(val binding: ItemListRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnDeleteRequest.setOnClickListener {
                onClickBasketListener.onClicked(asyncListDiffer.currentList[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordRVViewHolder {
        val binding =
            ItemListRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordRVViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: RecordRVViewHolder, position: Int) {
        with(holder.binding) {
            tvNumber.text = asyncListDiffer.currentList[position].bin
        }
    }
}

class RecordRVDiffUtilCallback : DiffUtil.ItemCallback<Record>() {
    override fun areItemsTheSame(
        oldItem: Record,
        newItem: Record
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Record,
        newItem: Record
    ): Boolean = oldItem == newItem
}