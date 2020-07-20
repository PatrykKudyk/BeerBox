package com.partos.beerbox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.Alcohol
import kotlinx.android.synthetic.main.row_cauldron.view.*


class CauldronRecyclerViewAdapter(val alcoholsList: ArrayList<Alcohol>) :
    RecyclerView.Adapter<CauldronViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CauldronViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_cauldron, parent, false)
        return CauldronViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return alcoholsList.size
    }

    override fun onBindViewHolder(holder: CauldronViewHolder, position: Int) {
        val context = holder.view.context
        val image = holder.view.row_cauldron_image
        val text = holder.view.row_cauldron_text
        val amount = holder.view.row_cauldron_amount
        when (alcoholsList[position].name) {
            context.getString(R.string.beer) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.beer))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
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
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
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
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                text.text = context.getText(R.string.vodka)
            }
            context.getString(R.string.gin) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.gin))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                text.text = context.getText(R.string.gin)
            }
            context.getString(R.string.champagne) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.champagne))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                text.text = context.getText(R.string.champagne)
            }
            context.getString(R.string.tequila) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.tequila))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                text.text = context.getText(R.string.tequila)
            }
            context.getString(R.string.whiskey) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.whiskey))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                text.text = context.getText(R.string.whiskey)
            }
            context.getString(R.string.rum) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.rum))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                text.text = context.getText(R.string.rum)
            }
            context.getString(R.string.energy_drink) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.energy_drink))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                text.text = context.getText(R.string.energy_drink)
            }
            context.getString(R.string.juice) -> {
                image.setImageDrawable(context.getDrawable(R.drawable.juice))
                amount.setText((alcoholsList[position].amount * 1000).toInt().toString() + " ml")
                when (alcoholsList[position].type) {
                    context.getString(R.string.juice_raspberry) -> text.text =
                        context.getText(R.string.juice_raspberry)
                    context.getString(R.string.juice_strawberry) -> text.text =
                        context.getText(R.string.juice_strawberry)

                }
            }
        }
    }

}

class CauldronViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}