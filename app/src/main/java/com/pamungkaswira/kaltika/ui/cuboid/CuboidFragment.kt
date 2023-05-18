package com.pamungkaswira.kaltika.ui.cuboid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.databinding.FragmentCuboidBinding
import com.pamungkaswira.kaltika.model.CuboidViewModel

class CuboidFragment : Fragment() {
    private lateinit var binding: FragmentCuboidBinding

    private val viewModel: CuboidViewModel by lazy {
        ViewModelProvider(requireActivity())[CuboidViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCuboidBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hasilCardView.visibility = View.GONE

        binding.luasPermukaanButton.setOnClickListener { hitungLuasPermukaan() }
        viewModel.getHasilLuasPermukaan().observe(requireActivity()) { showLuasPermukaan(it) }

        binding.volumeButton.setOnClickListener { hitungVolume() }
        viewModel.getHasilVolume().observe(requireActivity()) { showVolume(it) }
    }

    private fun hitungLuasPermukaan() {
        val panjang = binding.panjangTextInputEditText.text.toString()
        val lebar = binding.lebarTextInputEditText.text.toString()
        val tinggi = binding.tinggiTextInputEditText.text.toString()

        if (panjang.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.panjang_balok_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (lebar.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.lebar_balok_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (tinggi.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.tinggi_balok_feedback), Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungLuasPermukaan(panjang.toDouble(), lebar.toDouble(), tinggi.toDouble())
    }

    private fun showLuasPermukaan(result: Double?) {
        if (result == null) return

        binding.headerHasilTextView.text = getString(R.string.header_hasil_luas_permukaan)
        binding.hasilTextView.text = getString(R.string.hasil_luas_permukaan, result)

        binding.hasilCardView.visibility = View.VISIBLE
    }

    private fun hitungVolume() {
        val panjang = binding.panjangTextInputEditText.text.toString()
        val lebar = binding.lebarTextInputEditText.text.toString()
        val tinggi = binding.tinggiTextInputEditText.text.toString()

        if (panjang.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.panjang_balok_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (lebar.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.lebar_balok_feedback), Toast.LENGTH_LONG).show()
            return
        }
        else if (tinggi.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.tinggi_balok_feedback), Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungVolume(panjang.toDouble(), lebar.toDouble(), tinggi.toDouble())
    }

    private fun showVolume(result: Double?) {
        if (result == null) return

        binding.headerHasilTextView.text = getString(R.string.header_hasil_volume)
        binding.hasilTextView.text = getString(R.string.hasil_volume, result)

        binding.hasilCardView.visibility = View.VISIBLE
    }
}