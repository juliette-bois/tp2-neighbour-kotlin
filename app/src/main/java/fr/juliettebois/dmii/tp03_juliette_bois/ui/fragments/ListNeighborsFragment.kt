package fr.juliettebois.dmii.tp03_juliette_bois.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.juliettebois.dmii.tp03_juliette_bois.NavigationListener
import fr.juliettebois.dmii.tp03_juliette_bois.R
import fr.juliettebois.dmii.tp03_juliette_bois.adapters.ListNeighborHandler
import fr.juliettebois.dmii.tp03_juliette_bois.adapters.ListNeighborsAdapter
import fr.juliettebois.dmii.tp03_juliette_bois.databinding.ListNeighborsFragmentBinding
import fr.juliettebois.dmii.tp03_juliette_bois.di.DI
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor
import fr.juliettebois.dmii.tp03_juliette_bois.viewmodels.NeighborViewModel

class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var viewModel: NeighborViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        binding.neighborsList.layoutManager = LinearLayoutManager(context)
        binding.neighborsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        onCreateNeibor()

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.list_neighbor_title)
        }

        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
        this.setData()
    }

    private fun onCreateDialog(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.confirm_button)
            .setMessage(R.string.confirm_create_message)
            .setPositiveButton(
                R.string.yes,
                DialogInterface.OnClickListener { dialog, id ->
                    DI.repository.deleteNeighbour(neighbor)
                    binding.neighborsList.adapter?.notifyDataSetChanged()
                }
            )
            .setNegativeButton(
                R.string.no,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                    Toast.makeText(context, R.string.confirm_created_message, Toast.LENGTH_SHORT).show()
                }
            )
        builder.create().show()
    }

    override fun onDeleteNeibor(neighbor: Neighbor) {
        onCreateDialog(neighbor)
    }

    private fun onCreateNeibor() {
        binding.addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighbourFragment())
        }
    }

    private fun setData() {
        viewModel.neighbors.observe(viewLifecycleOwner) {
            val adapter = ListNeighborsAdapter(it, this)
            binding.neighborsList.adapter = adapter
        }
    }
}
