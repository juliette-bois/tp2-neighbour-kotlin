package fr.juliettebois.dmii.tp03_juliette_bois.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.juliettebois.dmii.tp03_juliette_bois.NavigationListener
import fr.juliettebois.dmii.tp03_juliette_bois.R
import fr.juliettebois.dmii.tp03_juliette_bois.adapters.ListNeighborHandler
import fr.juliettebois.dmii.tp03_juliette_bois.adapters.ListNeighborsAdapter
import fr.juliettebois.dmii.tp03_juliette_bois.data.NeighborRepository
import fr.juliettebois.dmii.tp03_juliette_bois.databinding.ListNeighborsFragmentBinding
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor

class ListNeighborsFragment: Fragment(), ListNeighborHandler {
    private lateinit var binding: ListNeighborsFragmentBinding

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        binding.neighborsList.adapter = adapter
    }

    private fun onCreateDialog(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.confirm_button)
            .setMessage(R.string.confirm_create_message)
            .setPositiveButton(R.string.yes,
                DialogInterface.OnClickListener { dialog, id ->
                    NeighborRepository.getInstance().deleteNeighbour(neighbor)
                    binding.neighborsList.adapter?.notifyDataSetChanged()
                })
            .setNegativeButton(R.string.no,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                    Toast.makeText(context, R.string.confirm_created_message, Toast.LENGTH_SHORT).show()
                })
        builder.create().show()
    }

    override fun onDeleteNeibor(neighbor: Neighbor) {
        onCreateDialog(neighbor)
    }

    fun onCreateNeibor() {
        binding.addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        }
    }
}