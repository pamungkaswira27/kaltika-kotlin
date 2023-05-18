package com.pamungkaswira.kaltika.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.databinding.FragmentHistoryBinding
import com.pamungkaswira.kaltika.db.HistoryDb
import com.pamungkaswira.kaltika.db.HistoryEntity
import com.pamungkaswira.kaltika.model.history.HistoryViewModel
import com.pamungkaswira.kaltika.model.history.HistoryViewModelFactory

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter

    private val viewModel: HistoryViewModel by lazy {
        val db = HistoryDb.getInstance(requireContext())
        val factory = HistoryViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyAdapter = HistoryAdapter()
        with(binding.historyRecyclerView) {
            adapter = historyAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.emptyTextView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            historyAdapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuClearAll) {
            clearData()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun clearData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.clear_confirmation)
            .setPositiveButton(getString(R.string.yes_confirmation)) {_, _ -> viewModel.clearData()}
            .setNegativeButton(getString(R.string.no_confirmation)) { dialog, _ -> dialog.cancel() }
            .show()
    }
}