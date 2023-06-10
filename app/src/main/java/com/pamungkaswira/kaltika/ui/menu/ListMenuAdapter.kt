package com.pamungkaswira.kaltika.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.databinding.ListMenuItemBinding
import com.pamungkaswira.kaltika.network.FormulaApi

class ListMenuAdapter : RecyclerView.Adapter<ListMenuAdapter.ViewHolder>() {
    private val menuData = mutableListOf<Formula>()

    class ViewHolder(private val binding: ListMenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(formula: Formula) = with(binding) {
            Glide.with(menuImageView.context)
                .load(FormulaApi.getFormulaUrl(formula.imageId))
                .error(R.drawable.broken_image)
                .into(menuImageView)

            menuNameTextView.text = formula.name
            categoryTextView.text = formula.category

            root.setOnClickListener {
                when {
                    menuNameTextView.text.toString() == "Kubus"
                    -> it.findNavController().navigate(R.id.menuFragment_to_CubeFragment)
                    menuNameTextView.text.toString() == "Balok"
                    -> it.findNavController().navigate(R.id.menuFragment_to_CuboidFragment)
                    menuNameTextView.text.toString() == "Aritmatika"
                    -> it.findNavController().navigate(R.id.menuFragment_to_ArithmeticFragment)
                    menuNameTextView.text.toString() == "Geometri"
                    -> it.findNavController().navigate(R.id.menuFragment_to_GeometryFragment)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListMenuItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuData[position])
    }

    fun updateData(newData: List<Formula>) {
        menuData.clear()
        menuData.addAll(newData)
        notifyDataSetChanged()
    }
}