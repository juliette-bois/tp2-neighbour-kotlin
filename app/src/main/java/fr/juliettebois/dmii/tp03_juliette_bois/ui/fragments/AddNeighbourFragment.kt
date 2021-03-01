package fr.juliettebois.dmii.tp03_juliette_bois.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import fr.juliettebois.dmii.tp03_juliette_bois.databinding.AddNeighborBinding
import java.util.*
import com.afollestad.vvalidator.form
import fr.juliettebois.dmii.tp03_juliette_bois.NavigationListener
import fr.juliettebois.dmii.tp03_juliette_bois.R
import fr.juliettebois.dmii.tp03_juliette_bois.repositories.NeighborRepository
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor

class AddNeighbourFragment: Fragment() {
    private lateinit var binding: AddNeighborBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = AddNeighborBinding.inflate(inflater, container, false)

        onValidateClick()

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.add_neighbor_title)
        }

        return binding.root
    }

    private fun onValidateClick() {
        form {
            input(binding.image) {
                isNotEmpty().description(R.string.empty_field_message)
                isUrl().description(R.string.unvalid_url_message)
            }
            input(binding.name) {
                isNotEmpty().description(R.string.empty_field_message)
            }
            input(binding.phone) {
                isNotEmpty().description(R.string.empty_field_message)
                length().exactly(10).description(R.string.unvalid_phone_number_message)
                //matches("/^(06|07)\\d{8}/gi").description(R.string.unvalid_phone_number_message)
            }
            input(binding.website) {
                isNotEmpty().description(R.string.empty_field_message)
                isUrl().description(R.string.unvalid_url_message)
            }
            input(binding.adress) {
                isNotEmpty().description(R.string.empty_field_message)
                isEmail().description(R.string.unvalid_email_message)
            }
            input(binding.about) {
                isNotEmpty().description(R.string.empty_field_message)
                length().atMost(30).description(R.string.longer_content_message)
            }

            //binding.validateNeighbor.isEnabled = true

            submitWith(binding.validateNeighbor) { result ->
                var neighbor = Neighbor(
                        UUID.randomUUID().mostSignificantBits,
                        binding.name.text.toString(),
                        binding.image.text.toString(),
                        binding.adress.text.toString(),
                        binding.phone.text.toString(),
                        binding.about.text.toString(),
                        false,
                        binding.website.text.toString()
                )
                NeighborRepository.getInstance().createNeighbor(neighbor)
                Toast.makeText(context, binding.name.text.toString() + " a été créé", Toast.LENGTH_LONG).show()

                // redirect to the list
                (activity as? NavigationListener)?.let {
                    it.showFragment(ListNeighborsFragment())
                }
            }
        }.validate()
    }
}