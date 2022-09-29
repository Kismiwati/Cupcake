package com.kismiwati.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kismiwati.cupcake.databinding.FragmentPickupBinding
import com.kismiwati.cupcake.model.OrderViewModel

/**
 * [PickupFragment] digunakan untuk memilih tanggal pengambilan pesanan
 */
class PickupFragment : Fragment() {

    // bagian ini digunakan untuk menyesuaikan binding dengan layout fragment_pickup
    private var binding: FragmentPickupBinding? = null

    // digunakan untuk mendegasi viewModel dari artefack fragment
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickupFragment = this@PickupFragment
        }
    }

    /**
     * bagian ini digunakan untuk melihat/ menampilkan ringkasan pesanan
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    /**
     *method dipanggil saat tampilan terkait dengan fragmen
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}