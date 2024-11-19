package com.sparkfusion.ef_test_task.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sparkfusion.data.entity.AuthorDataEntity
import com.sparkfusion.ef_test_task.databinding.ItemUserBinding

class AuthorsAdapter(private val authors: List<AuthorDataEntity>) : RecyclerView.Adapter<AuthorsAdapter.AuthorViewHolder>() {

    class AuthorViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(author: AuthorDataEntity) {
            binding.userMessageTextView.text = author.fullName
            Glide.with(binding.root.context)
                .load(author.avatar)
                .into(binding.userImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AuthorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.bind(authors[position])
    }

    override fun getItemCount(): Int {
        return authors.size
    }
}













