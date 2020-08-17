package com.partos.gamebox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.activities.MainActivity
import com.partos.gamebox.models.Alcohol
import kotlinx.android.synthetic.main.row_alcohol.view.*

class AlcoholListRecyclerViewAdapter(val alcoholsList: ArrayList<Alcohol>) :
    RecyclerView.Adapter<AlcoholListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlcoholListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_alcohol, parent, false)
        return AlcoholListViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return alcoholsList.size
    }

    override fun onBindViewHolder(holder: AlcoholListViewHolder, position: Int) {
        val context = holder.view.context
        val image = holder.view.row_alcohol_image
        val text = holder.view.row_alcohol_text
        when (alcoholsList[position].name) {
            context.getString(R.string.beer) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.beer))
                when (alcoholsList[position].type) {
                    context.getString(R.string.beer_lager) -> text.text =
                        context.getText(R.string.beer_lager)
                    context.getString(R.string.beer_dark) -> text.text =
                        context.getText(R.string.beer_dark)
                    context.getString(R.string.beer_wheat) -> text.text =
                        context.getText(R.string.beer_wheat)
                    context.getString(R.string.beer_ipa) -> text.text =
                        context.getText(R.string.beer_ipa)
                    context.getString(R.string.beer_porter) -> text.text =
                        context.getText(R.string.beer_porter)
                }
            }
            context.getString(R.string.wine) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.wine))
                when (alcoholsList[position].type) {
                    context.getString(R.string.wine_red_dry) -> text.text =
                        context.getText(R.string.wine_red_dry)
                    context.getString(R.string.wine_red_sweet) -> text.text =
                        context.getText(R.string.wine_red_sweet)
                    context.getString(R.string.wine_white_dry) -> text.text =
                        context.getText(R.string.wine_white_dry)
                    context.getString(R.string.wine_white_sweet) -> text.text =
                        context.getText(R.string.wine_white_sweet)
                }
            }
            context.getString(R.string.vodka) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.vodka))
                text.text = context.getText(R.string.vodka)
            }
            context.getString(R.string.gin) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.gin))
                text.text = context.getText(R.string.gin)
            }
            context.getString(R.string.champagne) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.champagne))
                text.text = context.getText(R.string.champagne)
            }
            context.getString(R.string.tequila) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.tequila))
                text.text = context.getText(R.string.tequila)
            }
            context.getString(R.string.whiskey) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.whiskey))
                text.text = context.getText(R.string.whiskey)
            }
            context.getString(R.string.rum) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.rum))
                text.text = context.getText(R.string.rum)
            }
            context.getString(R.string.energy_drink) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.energy_drink))
                text.text = context.getText(R.string.energy_drink)
            }
            context.getString(R.string.juice) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.juice))
                when (alcoholsList[position].type) {
                    context.getString(R.string.juice_raspberry) -> text.text =
                        context.getText(R.string.juice_raspberry)
                    context.getString(R.string.juice_strawberry) -> text.text =
                        context.getText(R.string.juice_strawberry)
                }
            }
        }

        holder.view.row_alcohol_card.setOnClickListener {
            MyApp.alcoholList.add(
                Alcohol(
                    alcoholsList[position].name,
                    0.0,
                    alcoholsList[position].type
                )
            )
            (holder.view.context as MainActivity)
                .supportFragmentManager
                .popBackStack()
        }
    }

}


class AlcoholListViewHolder(val view: View) : RecyclerView.ViewHolder(view)