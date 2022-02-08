package test.test.testLastFM.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import test.test.testLastFM.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val mViewModel: MainViewModel by viewModels()
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mArtistsAdapter: ArtistsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViews()
    }

    private fun initViews() {
        mArtistsAdapter = ArtistsAdapter()
        mArtistsAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        mBinding.recyclerList.adapter = mArtistsAdapter
        mBinding.recyclerList.layoutManager = LinearLayoutManager(this)
        mBinding.swipeToRefresh.setOnRefreshListener(this)

        mViewModel.artists.observe(this) { artists ->
            mArtistsAdapter.submitList(artists)
        }
        mViewModel.refreshing.observe(this) {
            mBinding.swipeToRefresh.isRefreshing = it
        }
    }

    override fun onRefresh() {
        mViewModel.fetchData()
    }
}