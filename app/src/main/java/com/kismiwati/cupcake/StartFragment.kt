package com.kismiwati.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kismiwati.cupcake.databinding.FragmentStartBinding
import com.kismiwati.cupcake.model.OrderViewModel

/**
 * bagian ini merupakan layar pertama aplikasi
 * pengguna dapat memesan beberapa banyak cupcake yang akan dipesan.
 */
class StartFragment : Fragment() {

    // bagian ini digunakan untuk menyesuaikan binding dengan fragment_start
    private var binding: FragmentStartBinding? = null

    // bagian ini untuk mendelegasi properti dari artefak fragment
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    /**
     * bagian ini untuk mulai memesan dengan jumlah yang diinginkan dan menavigasikan kelayar berikutnya.
     */
    fun orderCupcake(quantity: Int) {
        // bagian ini digunakan untuk memperbaharui tampilan dengan kuantitas
        sharedViewModel.setQuantity(quantity)

        // bagian ini akan secara default memilih rasa vanila jika belum ada rasa yang dipilih
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // bagian ini untuk mengarahkan ke tujuan berikutnya untuk memilih rasa
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    /**
     *  method dipanggil saat tampilan terkait dengan fragmen
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}