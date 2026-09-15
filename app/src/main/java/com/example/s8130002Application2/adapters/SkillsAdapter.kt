package com.example.s8130002Application2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s8130002Application2.databinding.ItemSkillBinding
import com.example.s8130002Application2.models.SkillItem

class SkillsAdapter(private val skills: List<SkillItem>) :
    RecyclerView.Adapter<SkillsAdapter.SkillViewHolder>() {

    inner class SkillViewHolder(private val binding: ItemSkillBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(skill: SkillItem) {
            binding.apply {
                skillNameTextView.text = skill.name
                skillProficiencyTextView.text = "${skill.proficiency}%"
                skillProgressBar.progress = skill.proficiency
                skillProgressBar.progressDrawable.setTint(skill.color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val binding = ItemSkillBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SkillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        holder.bind(skills[position])
    }

    override fun getItemCount(): Int = skills.size
}
