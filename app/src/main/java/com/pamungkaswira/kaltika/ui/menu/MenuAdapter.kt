package com.pamungkaswira.kaltika.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.databinding.MenuItemBinding

class MenuAdapter(private val menuDatas: List<MenuData>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    class ViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menuData: MenuData) = with(binding) {
            menuImageView.setImageResource(menuData.imageId)
            menuNameTextView.text = menuData.name
            categoryTextView.text = menuData.category

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
        val binding = MenuItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuDatas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuDatas[position])
    }
}