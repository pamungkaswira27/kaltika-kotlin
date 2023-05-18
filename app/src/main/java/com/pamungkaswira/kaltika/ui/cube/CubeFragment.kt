package com.pamungkaswira.kaltika.ui.cube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.databinding.FragmentCubeBinding
import com.pamungkaswira.kaltika.model.CubeViewModel

class CubeFragment : Fragment() {

    private lateinit var binding: FragmentCubeBinding

    private val viewModel: CubeViewModel by lazy {
        ViewModelProvider(requireActivity())[CubeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCubeBinding.inflate(layoutInflater, container, false)
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
        val rusuk = binding.rusukTextInputEditText.text.toString()

        if (rusuk.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.panjang_rusuk_feedback), Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungLuasPermukaan(rusuk.toDouble())
    }

    private fun showLuasPermukaan(result: Double?) {
        if (result == null) return

        binding.headerHasilTextView.text = getString(R.string.header_hasil_luas_permukaan)
        binding.hasilTextView.text = getString(R.string.hasil_luas_permukaan, result)

        binding.hasilCardView.visibility = View.VISIBLE
    }

    private fun hitungVolume() {
        val rusuk = binding.rusukTextInputEditText.text.toString()

        if (rusuk.isEmpty())
        {
            Toast.makeText(requireContext(), getString(R.string.panjang_rusuk_feedback), Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungVolume(rusuk.toDouble())
    }

    private fun showVolume(result: Double?) {
        if (result == null) return

        binding.headerHasilTextView.text = getString(R.string.header_hasil_volume)
        binding.hasilTextView.text = getString(R.string.hasil_volume, result)

        binding.hasilCardView.visibility = View.VISIBLE
    }
}