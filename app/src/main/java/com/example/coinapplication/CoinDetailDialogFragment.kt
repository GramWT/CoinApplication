package com.example.coinapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.coinapplication.databinding.FragmentCoinDetailDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CoinDetailDialogFragment : BottomSheetDialog() {

    private val binding: FragmentCoinDetailDialogBinding by lazy {
        FragmentCoinDetailDialogBinding.inflate(layoutInflater)
    }


    companion object{
        private const val TAG_COIN_DETAIL = "coinDetail"

        fun requestShow(fragmentManager: FragmentManager) {
            CoinDetailDialogFragment().show(
                fragmentManager,
                TAG_COIN_DETAIL
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}