package com.elsharif.bookhaven

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.elsharif.bookhaven.Adapters.HomeAdapter
import com.elsharif.bookhaven.Models.HomeModel
import com.elsharif.bookhaven.Repository.MainRepo
import com.elsharif.bookhaven.databinding.ActivityMainBinding
import com.elsharif.bookhaven.utlis.MyResponsers
import com.elsharif.bookhaven.utlis.SpringScrollHelper
import com.elsharif.bookhaven.utlis.loadBannerAd
import com.elsharif.bookhaven.utlis.removeWithAnim
import com.elsharif.bookhaven.utlis.showWithAnim
import com.elsharif.bookhaven.viewmodel.MainViewModel
import com.elsharif.bookhaven.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val activity = this
    val list: ArrayList<HomeModel> = ArrayList()
    val adapter = HomeAdapter(list, activity)
    private val TAG = "MainActivity"
    private val repo = MainRepo(activity)
    private val viewModel by lazy {
        ViewModelProvider(activity, MainViewModelFactory(repo))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            mBannerAd.loadBannerAd()
            mRvHome.adapter = adapter
            SpringScrollHelper().attachToRecyclerView(mRvHome)
            viewModel.getHomeData()
            handleHomeBackend()

            mLayoutError.mTryAgainBtn.setOnClickListener {
                viewModel.getHomeData()
            }

        }

    }

    private fun handleHomeBackend() {
        viewModel.homeLiveData.observe(activity) {
            when (it) {
                is MyResponsers.Error -> {
                    Log.i(TAG, "handleHomeBackend: ${it.errorMessage}")
                    binding.mErrorHolder.showWithAnim()
                    binding.mLoaderHolder.removeWithAnim()
                }

                is MyResponsers.Loading -> {
                    Log.i(TAG, "handleHomeBackend: Loading...")
                    binding.mErrorHolder.removeWithAnim()
                    binding.mLoaderHolder.showWithAnim()
                }

                is MyResponsers.Success -> {
                    binding.mErrorHolder.removeWithAnim()
                    binding.mLoaderHolder.removeWithAnim()
                    val tempList = it.data
                    list.clear()
                    Log.i(TAG, "handleHomeBackend: Success Called $tempList ")
                    tempList?.forEach {
                        list.add(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }

}