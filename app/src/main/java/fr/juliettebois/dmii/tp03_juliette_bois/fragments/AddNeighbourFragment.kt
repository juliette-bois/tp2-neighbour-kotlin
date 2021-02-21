package fr.juliettebois.dmii.tp03_juliette_bois.fragments

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
import fr.juliettebois.dmii.tp03_juliette_bois.data.NeighborRepository
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

        return binding.root
    }

    private fun onValidateClick() {
        form {
            input(binding.image) {
                isNotEmpty().description("Please enter a value!")
                isUrl().description("Please enter a valid url!")
            }
            input(binding.name) {
                isNotEmpty().description("Please enter a value!")
            }
            input(binding.phone) {
                isNotEmpty().description("Please enter a value!")
                length().exactly(10).description("Please enter a valid phone number!")
                matches("/^0(6|7)[0-9]{8}").description("Please enter a valid phone number!")
            }
            input(binding.website) {
                isNotEmpty().description("Please enter a value!")
                isUrl().description("Please enter a valid url!")
            }
            input(binding.adress) {
                isNotEmpty().description("Please enter a value!")
                isEmail().description("Please enter a valid email!")
            }
            input(binding.about) {
                isNotEmpty().description("Please enter a value!")
                length().atMost(30).description("Please enter a shorter content!")
            }

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