package br.com.doghero.dhproject.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.doghero.dhproject.databinding.MyHeroItemBinding
import br.com.doghero.dhproject.model.Hero
import com.squareup.picasso.Picasso
import android.os.Build
import android.text.Spanned
import br.com.doghero.dhproject.R
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.graphics.Color
import android.support.v4.content.ContextCompat


class MyHeroesAdapter(val context: Context, val type: HeroType) : RecyclerView.Adapter<MyHeroesAdapter.MyHeroesViewHolder>() {
    enum class HeroType {
        FAVORITE,
        RECENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHeroesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyHeroItemBinding.inflate(inflater, parent, false)
        return MyHeroesViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyHeroesViewHolder, position: Int) = holder.bind(items[position])

    private var items: List<Hero> = ArrayList()

    fun setData(items: List<Hero>) {
        this.items = items
        notifyDataSetChanged()
    }

    @Suppress("DEPRECATION")
    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    inner class MyHeroesViewHolder(private val binding: MyHeroItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hero) {
            binding.txtHostName.text = item.user.firstName
            binding.txtHostNeighborhood.text = item.addressNeighborhood
            binding.rating.rating = 5.0f
            val stars = binding.rating.progressDrawable as LayerDrawable
            stars.setTint(ContextCompat.getColor(context, R.color.yellow_F6CE54))

            val price = "<b>" + Math.round(item.price).toString() + "</b>"
            binding.txtHostPrice.text = fromHtml(context.getString(R.string.real_symbol) + price)

            Picasso.with(context)
                    .load(item.user.imageUrl)
                    .into(binding.ivHost)

            if (item.isSuperhero) {
                binding.ivBadge.visibility = View.VISIBLE
            } else {
                binding.ivBadge.visibility = View.GONE
            }

            if (type == HeroType.FAVORITE) {
                binding.btFavorite.visibility = View.VISIBLE
                binding.btFavorite.isChecked = true
            } else {
                binding.btFavorite.visibility = View.GONE
            }
        }
    }
}