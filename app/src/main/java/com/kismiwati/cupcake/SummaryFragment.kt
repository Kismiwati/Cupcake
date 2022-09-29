package com.kismiwati.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kismiwati.cupcake.databinding.FragmentSummaryBinding
import com.kismiwati.cupcake.model.OrderViewModel

/**
 * [SummaryFragment] berisi ringkasan pesanan dengan tombol untuk membagikan pesanan
 */
class SummaryFragment : Fragment() {

    // bagian ini digunakan untuk menyesuaikan binding dengan layout fragment_summary
    private var binding: FragmentSummaryBinding? = null

    // bagian ini untuk mendelegasi properti viewmodel dari artefak
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    /**
     *bagian ini digunakan untuk mengirimkan pesanan ke aplikasi lain melalui intent
     */
    fun sendOrder() {
        Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }

    /**
     * method dipanggil saat tampilan terkait dengan fragmen
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}