package com.kismiwati.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kismiwati.cupcake.R
import com.kismiwati.cupcake.databinding.FragmentFlavorBinding
import com.kismiwati.cupcake.model.OrderViewModel

class FlavorFragment : Fragment() {

    // bagian ini digunakan untuk menyesuaikan binding dengan fragmentflavorbinding
    // tampilan ditampilkan ke fragmen

    private var binding: FragmentFlavorBinding? = null

    // bagian ini digunakan untuk mendelegasi properti dari artafak fragmen
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // bagian ini digunakan untuk menentukan fragmen sebagai pemilik.
            lifecycleOwner = viewLifecycleOwner

            // bagian ini digunakan untuk menetapkan model tampilan ke properti di kelas binding
            viewModel = sharedViewModel

            // bagian ini digunakan untuk menetapkan fragmen
            flavorFragment = this@FlavorFragment
        }
    }

    /**
     * bagian ini digunakan untuk memilih tanggal pengambilan
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    /**
     *method fragment dipanggil saat tampilan terkait dengan fragmen
      */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}