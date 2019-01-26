package br.com.doghero.dhproject.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import br.com.doghero.dhproject.R
import br.com.doghero.dhproject.databinding.ActivityMyHeroesBinding
import br.com.doghero.dhproject.viewmodel.MyHeroesViewModel


class MyHeroesActivity : AppCompatActivity() {
    private lateinit var viewModel: MyHeroesViewModel
    private lateinit var binding: ActivityMyHeroesBinding
    private lateinit var adapterRecent: MyHeroesAdapter
    private lateinit var adapterFavorites: MyHeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_heroes)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_heroes)
        viewModel = ViewModelProviders.of(this).get(MyHeroesViewModel::class.java)
        adapterRecent = MyHeroesAdapter(applicationContext, MyHeroesAdapter.HeroType.RECENT)
        adapterFavorites = MyHeroesAdapter(applicationContext, MyHeroesAdapter.HeroType.FAVORITE)

        binding.rvHostedHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvFavHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvHostedHeroes.adapter = adapterRecent
        binding.rvFavHeroes.adapter = adapterFavorites
        subscribe()

        viewModel.getMyHeroes()
    }

    private fun subscribe() {
        viewModel.getMyHeroesObservable().observe(this, Observer {
            if (it != null) {
                adapterRecent.setData(it.recents)
                adapterFavorites.setData(it.favorites)
            }
        })
    }
}
