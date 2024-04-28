package com.example.coinapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinapplication.adapter.CoinAdapter
import com.example.coinapplication.databinding.FragmentMainBinding
import com.example.coinapplication.viewmodel.CoinViewModel

class MainFragment : Fragment(),BottomSheetOnClickListener {

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var viewModel: CoinViewModel
    private var coinAdapter: CoinAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.fetchNextPage()

        coinAdapter = CoinAdapter(this)

        myLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.coinRecyclerView.apply {
            this.adapter = coinAdapter
            this.layoutManager = myLayoutManager
            this.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = myLayoutManager.childCount
                    val totalItemCount = myLayoutManager.itemCount
                    val firstVisibleItemPosition = myLayoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    }
                }
            })
        }
        viewModel.observeCoinLiveData().observe(requireActivity(), Observer { coinList ->
            coinAdapter?.setData(coinList)
        })
    }

    override fun onClick() {
        CoinDetailDialogFragment.requestShow(childFragmentManager)
    }
}