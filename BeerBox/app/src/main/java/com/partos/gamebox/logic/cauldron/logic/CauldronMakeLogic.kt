package com.partos.gamebox.logic.cauldron.logic

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.gamebox.R
import com.partos.gamebox.models.Alcohol
import com.partos.gamebox.recycler.CauldronRecyclerViewAdapter
import com.partos.gamebox.recycler.MarginItemDecoration
import kotlin.random.Random

class CauldronMakeLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var alcoholArray: ArrayList<Int>
    private var litres = 0
    private lateinit var context: Context

    fun initFragment(rootView: View, alcoholsArray: ArrayList<Int>, litresGiven: Int) {
        attachViews(rootView)
        alcoholArray = alcoholsArray
        litres = litresGiven
        context = rootView.context
        val amountsList = generateAmountsList(rootView)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter =
            CauldronRecyclerViewAdapter(generateFinalAlcoholList(amountsList, rootView))
    }

    private fun generateFinalAlcoholList(
        amountsList: ArrayList<Alcohol>,
        rootView: View
    ): ArrayList<Alcohol> {
        val amountFinal = ArrayList<Alcohol>()
        var beer1 = 0.0
        var beer2 = 0.0
        var beer3 = 0.0
        var beer4 = 0.0
        var beer5 = 0.0
        var wine1 = 0.0
        var wine2 = 0.0
        var wine3 = 0.0
        var wine4 = 0.0
        var juice1 = 0.0
        var juice2 = 0.0
        var vodka = 0.0
        var gin = 0.0
        var champagne = 0.0
        var energy = 0.0
        var tequila = 0.0
        var whiskey = 0.0
        var rum = 0.0

        for (element in amountsList) {
            when (element.type) {
                context.getString(R.string.beer_lager) -> beer1 += element.amount
                context.getString(R.string.beer_dark) -> beer2 += element.amount
                context.getString(R.string.beer_wheat) -> beer3 += element.amount
                context.getString(R.string.beer_ipa) -> beer4 += element.amount
                context.getString(R.string.beer_porter) -> beer5 += element.amount
                context.getString(R.string.wine_red_dry) -> wine1 += element.amount
                context.getString(R.string.wine_red_sweet) -> wine2 += element.amount
                context.getString(R.string.wine_white_dry) -> wine3 += element.amount
                context.getString(R.string.wine_white_sweet) -> wine4 += element.amount
                context.getString(R.string.juice_raspberry) -> juice1 += element.amount
                context.getString(R.string.juice_strawberry) -> juice2 += element.amount
                context.getString(R.string.vodka) -> vodka += element.amount
                context.getString(R.string.gin) -> gin += element.amount
                context.getString(R.string.champagne) -> champagne += element.amount
                context.getString(R.string.whiskey) -> whiskey += element.amount
                context.getString(R.string.tequila) -> tequila += element.amount
                context.getString(R.string.rum) -> rum += element.amount
                context.getString(R.string.energy_drink) -> energy += element.amount
            }
        }
        if (beer1 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.beer),
                    beer1,
                    context.getString(R.string.beer_lager)
                )
            )
        }
        if (beer2 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.beer),
                    beer2,
                    context.getString(R.string.beer_dark)
                )
            )
        }
        if (beer3 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.beer),
                    beer3,
                    context.getString(R.string.beer_wheat)
                )
            )
        }
        if (beer4 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.beer),
                    beer4,
                    context.getString(R.string.beer_ipa)
                )
            )
        }
        if (beer5 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.beer),
                    beer5,
                    context.getString(R.string.beer_porter)
                )
            )
        }
        if (wine1 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.wine),
                    wine1,
                    context.getString(R.string.wine_red_dry)
                )
            )
        }
        if (wine2 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.wine),
                    wine2,
                    context.getString(R.string.wine_red_sweet)
                )
            )
        }
        if (wine3 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.wine),
                    wine3,
                    context.getString(R.string.wine_white_dry)
                )
            )
        }
        if (wine4 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.wine),
                    wine4,
                    context.getString(R.string.wine_white_sweet)
                )
            )
        }
        if (vodka != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.vodka),
                    vodka,
                    context.getString(R.string.vodka)
                )
            )
        }
        if (gin != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.gin),
                    gin,
                    context.getString(R.string.gin)
                )
            )
        }
        if (champagne != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.champagne),
                    champagne,
                    context.getString(R.string.champagne)
                )
            )
        }
        if (tequila != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.tequila),
                    tequila,
                    context.getString(R.string.tequila)
                )
            )
        }
        if (whiskey != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.whiskey),
                    whiskey,
                    context.getString(R.string.whiskey)
                )
            )
        }
        if (rum != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.rum),
                    rum,
                    context.getString(R.string.rum)
                )
            )
        }
        if (juice1 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.juice),
                    juice1,
                    context.getString(R.string.juice_raspberry)
                )
            )
        }
        if (juice2 != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.juice),
                    juice2,
                    context.getString(R.string.juice_strawberry)
                )
            )
        }
        if (energy != 0.0) {
            amountFinal.add(
                Alcohol(
                    context.getString(R.string.energy_drink),
                    energy,
                    context.getString(R.string.energy_drink)
                )
            )
        }
        return amountFinal
    }

    private fun generateAmountsList(rootView: View): ArrayList<Alcohol> {
        val finalList = ArrayList<Alcohol>()
        var left = litres.toDouble() as Double
        var isAny = false
        do {
            when (left) {
                in 2.0..2.999 -> {
                    isAny = false
                    if (alcoholArray.get(0) == 1) {
                        when (Random.nextInt(0, 5)) {
                            0 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    0.5,
                                    context.getString(R.string.beer_lager)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    0.5,
                                    context.getString(R.string.beer_dark)
                                )
                            )
                            2 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    0.5,
                                    context.getString(R.string.beer_wheat)
                                )
                            )
                            3 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    0.5,
                                    context.getString(R.string.beer_ipa)
                                )
                            )
                            4 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    0.5,
                                    context.getString(R.string.beer_porter)
                                )
                            )
                        }
                        left -= 0.5
                        isAny = true
                    }

                    if (alcoholArray?.get(8) == 1) {
                        when (Random.nextInt(0, 1)) {
                            0 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.juice),
                                    0.25,
                                    context.getString(R.string.juice_raspberry)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.juice),
                                    0.25,
                                    context.getString(R.string.juice_strawberry)
                                )
                            )
                        }
                        left -= 0.25
                        isAny = true
                    }

                    if (alcoholArray?.get(9) == 1) {
                        finalList.add(
                            Alcohol(
                                context.getString(R.string.energy_drink),
                                0.5,
                                context.getString(R.string.energy_drink)
                            )
                        )
                        left -= 0.5
                        isAny = true
                    }
                }

                in 3.0..Double.MAX_VALUE -> {
                    if (alcoholArray?.get(0) == 1) {
                        when (Random.nextInt(0, 5)) {
                            0 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    1.0,
                                    context.getString(R.string.beer_lager)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    1.0,
                                    context.getString(R.string.beer_dark)
                                )
                            )
                            2 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    1.0,
                                    context.getString(R.string.beer_wheat)
                                )
                            )
                            3 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    1.0,
                                    context.getString(R.string.beer_ipa)
                                )
                            )
                            4 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.beer),
                                    1.0,
                                    context.getString(R.string.beer_porter)
                                )
                            )
                        }
                        left -= 1.0
                        isAny = true
                    }

                    if (alcoholArray?.get(1) == 1) {
                        when (Random.nextInt(0, 3)) {
                            0 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.wine),
                                    0.75,
                                    context.getString(R.string.wine_red_dry)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.wine),
                                    0.75,
                                    context.getString(R.string.wine_red_sweet)
                                )
                            )
                            2 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.wine),
                                    0.75,
                                    context.getString(R.string.wine_white_dry)
                                )
                            )
                            3 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.wine),
                                    0.75,
                                    context.getString(R.string.wine_white_sweet)
                                )
                            )
                        }
                        left -= 0.75
                        isAny = true
                    }

                    if (alcoholArray?.get(4) == 1) {
                        finalList.add(
                            Alcohol(
                                context.getString(R.string.champagne),
                                0.75,
                                context.getString(R.string.champagne)
                            )
                        )
                        left -= 0.75
                    }

                    if (alcoholArray?.get(8) == 1) {
                        when (Random.nextInt(0, 1)) {
                            0 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.juice),
                                    0.25,
                                    context.getString(R.string.juice_raspberry)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    context.getString(R.string.juice),
                                    0.25,
                                    context.getString(R.string.juice_strawberry)
                                )
                            )
                        }
                        left -= 0.25
                        isAny = true
                    }

                    if (alcoholArray?.get(9) == 1) {
                        finalList.add(
                            Alcohol(
                                context.getString(R.string.energy_drink),
                                0.5,
                                context.getString(R.string.energy_drink)
                            )
                        )
                        left -= 0.5
                        isAny = true
                    }
                }
            }
        } while (isEnd(isAny, left))

        var additionalAlcohols = getAdditionalAlcohols()
        val amount: Double
        if (additionalAlcohols == 0) {
            if (alcoholArray?.get(0) == 1) {
                when (Random.nextInt(0, 5)) {
                    0 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.beer),
                            left,
                            context.getString(R.string.beer_lager)
                        )
                    )
                    1 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.beer),
                            left,
                            context.getString(R.string.beer_dark)
                        )
                    )
                    2 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.beer),
                            left,
                            context.getString(R.string.beer_wheat)
                        )
                    )
                    3 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.beer),
                            left,
                            context.getString(R.string.beer_ipa)
                        )
                    )
                    4 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.beer),
                            left,
                            context.getString(R.string.beer_porter)
                        )
                    )
                }
            }

            if (alcoholArray?.get(1) == 1) {
                when (Random.nextInt(0, 3)) {
                    0 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.wine),
                            left,
                            context.getString(R.string.wine_red_dry)
                        )
                    )
                    1 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.wine),
                            left,
                            context.getString(R.string.wine_red_sweet)
                        )
                    )
                    2 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.wine),
                            left,
                            context.getString(R.string.wine_white_dry)
                        )
                    )
                    3 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.wine),
                            left,
                            context.getString(R.string.wine_white_sweet)
                        )
                    )
                }
            }

            if (alcoholArray?.get(4) == 1) {
                finalList.add(
                    Alcohol(
                        context.getString(R.string.champagne),
                        left,
                        context.getString(R.string.champagne)
                    )
                )
            }

            if (alcoholArray?.get(8) == 1) {
                when (Random.nextInt(0, 1)) {
                    0 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.juice),
                            left,
                            context.getString(R.string.juice_raspberry)
                        )
                    )
                    1 -> finalList.add(
                        Alcohol(
                            context.getString(R.string.juice),
                            left,
                            context.getString(R.string.juice_strawberry)
                        )
                    )
                }
            }

            if (alcoholArray?.get(9) == 1) {
                finalList.add(
                    Alcohol(
                        context.getString(R.string.energy_drink),
                        left,
                        context.getString(R.string.energy_drink)
                    )
                )
            }
        } else {
            amount = left / additionalAlcohols.toDouble()
            if (alcoholArray?.get(2) == 1) {
                finalList.add(
                    Alcohol(
                        context.getString(R.string.vodka),
                        amount,
                        context.getString(R.string.vodka)
                    )
                )
            }
            if (alcoholArray?.get(3) == 1) {
                finalList.add(
                    Alcohol(
                        context.getString(R.string.gin),
                        amount,
                        context.getString(R.string.gin)
                    )
                )
            }
            if (alcoholArray?.get(5) == 1) {
                finalList.add(
                    Alcohol(
                        context.getString(R.string.tequila),
                        amount,
                        context.getString(R.string.tequila)
                    )
                )
            }
            if (alcoholArray?.get(6) == 1) {
                finalList.add(
                    Alcohol(
                        context.getString(R.string.whiskey),
                        amount,
                        context.getString(R.string.whiskey)
                    )
                )
            }
            if (alcoholArray?.get(7) == 1) {
                finalList.add(
                    Alcohol(
                        context.getString(R.string.rum),
                        amount,
                        context.getString(R.string.rum)
                    )
                )
            }
        }
        return finalList
    }

    private fun getAdditionalAlcohols(): Int {
        var alcohols = 0
        if (alcoholArray?.get(2) == 1)
            alcohols++
        if (alcoholArray?.get(3) == 1)
            alcohols++
        if (alcoholArray?.get(5) == 1)
            alcohols++
        if (alcoholArray?.get(6) == 1)
            alcohols++
        if (alcoholArray?.get(7) == 1)
            alcohols++
        return alcohols
    }

    private fun isEnd(isAny: Boolean, left: Double): Boolean {
        return if (isAny) {
            left > 2
        } else
            false
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.cauldron_make_recycler)
    }
}