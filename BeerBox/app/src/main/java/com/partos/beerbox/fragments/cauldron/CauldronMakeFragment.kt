package com.partos.beerbox.fragments.cauldron

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.beerbox.R
import com.partos.beerbox.models.Alcohol
import com.partos.beerbox.recycler.CauldronRecyclerViewAdapter
import com.partos.beerbox.recycler.MarginItemDecoration
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "alcoholArray"
private const val ARG_PARAM2 = "litres"

/**
 * A simple [Fragment] subclass.
 * Use the [CauldronMakeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CauldronMakeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var alcoholArray: ArrayList<Int>? = null
    private var litres: Int? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var rootView: View

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            alcoholArray = it.getIntegerArrayList(ARG_PARAM1)
            litres = it.getInt(ARG_PARAM2)
        }
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cauldron_make, container, false)
        initFragment()
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(alcoholsArray: ArrayList<Int>, litres: Int) =
            CauldronMakeFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(ARG_PARAM1, alcoholsArray)
                    putInt(ARG_PARAM2, litres)
                }
            }
    }

    private fun initFragment() {
        attachViews()
        val amountsList = generateAmountsList()
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = CauldronRecyclerViewAdapter(generateFinalAlcoholList(amountsList))
    }

    private fun generateFinalAlcoholList(amountsList: ArrayList<Alcohol>): ArrayList<Alcohol> {
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
                rootView.context.getString(R.string.beer_lager) -> beer1 += element.amount
                rootView.context.getString(R.string.beer_dark) -> beer2 += element.amount
                rootView.context.getString(R.string.beer_wheat) -> beer3 += element.amount
                rootView.context.getString(R.string.beer_ipa) -> beer4 += element.amount
                rootView.context.getString(R.string.beer_porter) -> beer5 += element.amount
                rootView.context.getString(R.string.wine_red_dry) -> wine1 += element.amount
                rootView.context.getString(R.string.wine_red_sweet) -> wine2 += element.amount
                rootView.context.getString(R.string.wine_white_dry) -> wine3 += element.amount
                rootView.context.getString(R.string.wine_white_sweet) -> wine4 += element.amount
                rootView.context.getString(R.string.juice_raspberry) -> juice1 += element.amount
                rootView.context.getString(R.string.juice_strawberry) -> juice2 += element.amount
                rootView.context.getString(R.string.vodka) -> vodka += element.amount
                rootView.context.getString(R.string.gin) -> gin += element.amount
                rootView.context.getString(R.string.champagne) -> champagne += element.amount
                rootView.context.getString(R.string.whiskey) -> whiskey += element.amount
                rootView.context.getString(R.string.tequila) -> tequila += element.amount
                rootView.context.getString(R.string.rum) -> rum += element.amount
                rootView.context.getString(R.string.energy_drink) -> energy += element.amount
            }
        }
        if (beer1 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.beer),
                    beer1,
                    rootView.context.getString(R.string.beer_lager)
                )
            )
        }
        if (beer2 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.beer),
                    beer2,
                    rootView.context.getString(R.string.beer_dark)
                )
            )
        }
        if (beer3 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.beer),
                    beer3,
                    rootView.context.getString(R.string.beer_wheat)
                )
            )
        }
        if (beer4 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.beer),
                    beer4,
                    rootView.context.getString(R.string.beer_ipa)
                )
            )
        }
        if (beer5 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.beer),
                    beer5,
                    rootView.context.getString(R.string.beer_porter)
                )
            )
        }
        if (wine1 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.wine),
                    wine1,
                    rootView.context.getString(R.string.wine_red_dry)
                )
            )
        }
        if (wine2 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.wine),
                    wine2,
                    rootView.context.getString(R.string.wine_red_sweet)
                )
            )
        }
        if (wine3 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.wine),
                    wine3,
                    rootView.context.getString(R.string.wine_white_dry)
                )
            )
        }
        if (wine4 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.wine),
                    wine4,
                    rootView.context.getString(R.string.wine_white_sweet)
                )
            )
        }
        if (vodka != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.vodka),
                    vodka,
                    rootView.context.getString(R.string.vodka)
                )
            )
        }
        if (gin != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.gin),
                    gin,
                    rootView.context.getString(R.string.gin)
                )
            )
        }
        if (champagne != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.champagne),
                    champagne,
                    rootView.context.getString(R.string.champagne)
                )
            )
        }
        if (tequila != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.tequila),
                    tequila,
                    rootView.context.getString(R.string.tequila)
                )
            )
        }
        if (whiskey != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.whiskey),
                    whiskey,
                    rootView.context.getString(R.string.whiskey)
                )
            )
        }
        if (rum != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.rum),
                    rum,
                    rootView.context.getString(R.string.rum)
                )
            )
        }
        if (juice1 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.juice),
                    juice1,
                    rootView.context.getString(R.string.juice_raspberry)
                )
            )
        }
        if (juice2 != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.juice),
                    juice2,
                    rootView.context.getString(R.string.juice_strawberry)
                )
            )
        }
        if (energy != 0.0) {
            amountFinal.add(
                Alcohol(
                    rootView.context.getString(R.string.energy_drink),
                    energy,
                    rootView.context.getString(R.string.energy_drink)
                )
            )
        }
        return amountFinal
    }

    private fun generateAmountsList(): ArrayList<Alcohol> {
        val finalList = ArrayList<Alcohol>()
        var left = litres?.toDouble() as Double
        var isAny = false
        do {
            when (left) {
                in 2.0..2.999 -> {
                    isAny = false
                    if (alcoholArray?.get(0) == 1) {
                        when (Random.nextInt(0, 5)) {
                            0 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    0.5,
                                    rootView.context.getString(R.string.beer_lager)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    0.5,
                                    rootView.context.getString(R.string.beer_dark)
                                )
                            )
                            2 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    0.5,
                                    rootView.context.getString(R.string.beer_wheat)
                                )
                            )
                            3 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    0.5,
                                    rootView.context.getString(R.string.beer_ipa)
                                )
                            )
                            4 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    0.5,
                                    rootView.context.getString(R.string.beer_porter)
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
                                    rootView.context.getString(R.string.juice),
                                    0.25,
                                    rootView.context.getString(R.string.juice_raspberry)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.juice),
                                    0.25,
                                    rootView.context.getString(R.string.juice_strawberry)
                                )
                            )
                        }
                        left -= 0.25
                        isAny = true
                    }

                    if (alcoholArray?.get(9) == 1) {
                        finalList.add(
                            Alcohol(
                                rootView.context.getString(R.string.energy_drink),
                                0.5,
                                rootView.context.getString(R.string.energy_drink)
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
                                    rootView.context.getString(R.string.beer),
                                    1.0,
                                    rootView.context.getString(R.string.beer_lager)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    1.0,
                                    rootView.context.getString(R.string.beer_dark)
                                )
                            )
                            2 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    1.0,
                                    rootView.context.getString(R.string.beer_wheat)
                                )
                            )
                            3 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    1.0,
                                    rootView.context.getString(R.string.beer_ipa)
                                )
                            )
                            4 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.beer),
                                    1.0,
                                    rootView.context.getString(R.string.beer_porter)
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
                                    rootView.context.getString(R.string.wine),
                                    0.75,
                                    rootView.context.getString(R.string.wine_red_dry)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.wine),
                                    0.75,
                                    rootView.context.getString(R.string.wine_red_sweet)
                                )
                            )
                            2 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.wine),
                                    0.75,
                                    rootView.context.getString(R.string.wine_white_dry)
                                )
                            )
                            3 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.wine),
                                    0.75,
                                    rootView.context.getString(R.string.wine_white_sweet)
                                )
                            )
                        }
                        left -= 0.75
                        isAny = true
                    }

                    if (alcoholArray?.get(4) == 1) {
                        finalList.add(
                            Alcohol(
                                rootView.context.getString(R.string.champagne),
                                0.75,
                                rootView.context.getString(R.string.champagne)
                            )
                        )
                        left -= 0.75
                    }

                    if (alcoholArray?.get(8) == 1) {
                        when (Random.nextInt(0, 1)) {
                            0 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.juice),
                                    0.25,
                                    rootView.context.getString(R.string.juice_raspberry)
                                )
                            )
                            1 -> finalList.add(
                                Alcohol(
                                    rootView.context.getString(R.string.juice),
                                    0.25,
                                    rootView.context.getString(R.string.juice_strawberry)
                                )
                            )
                        }
                        left -= 0.25
                        isAny = true
                    }

                    if (alcoholArray?.get(9) == 1) {
                        finalList.add(
                            Alcohol(
                                rootView.context.getString(R.string.energy_drink),
                                0.5,
                                rootView.context.getString(R.string.energy_drink)
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
                            rootView.context.getString(R.string.beer),
                            left,
                            rootView.context.getString(R.string.beer_lager)
                        )
                    )
                    1 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.beer),
                            left,
                            rootView.context.getString(R.string.beer_dark)
                        )
                    )
                    2 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.beer),
                            left,
                            rootView.context.getString(R.string.beer_wheat)
                        )
                    )
                    3 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.beer),
                            left,
                            rootView.context.getString(R.string.beer_ipa)
                        )
                    )
                    4 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.beer),
                            left,
                            rootView.context.getString(R.string.beer_porter)
                        )
                    )
                }
            }

            if (alcoholArray?.get(1) == 1) {
                when (Random.nextInt(0, 3)) {
                    0 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.wine),
                            left,
                            rootView.context.getString(R.string.wine_red_dry)
                        )
                    )
                    1 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.wine),
                            left,
                            rootView.context.getString(R.string.wine_red_sweet)
                        )
                    )
                    2 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.wine),
                            left,
                            rootView.context.getString(R.string.wine_white_dry)
                        )
                    )
                    3 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.wine),
                            left,
                            rootView.context.getString(R.string.wine_white_sweet)
                        )
                    )
                }
            }

            if (alcoholArray?.get(4) == 1) {
                finalList.add(
                    Alcohol(
                        rootView.context.getString(R.string.champagne),
                        left,
                        rootView.context.getString(R.string.champagne)
                    )
                )
            }

            if (alcoholArray?.get(8) == 1) {
                when (Random.nextInt(0, 1)) {
                    0 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.juice),
                            left,
                            rootView.context.getString(R.string.juice_raspberry)
                        )
                    )
                    1 -> finalList.add(
                        Alcohol(
                            rootView.context.getString(R.string.juice),
                            left,
                            rootView.context.getString(R.string.juice_strawberry)
                        )
                    )
                }
            }

            if (alcoholArray?.get(9) == 1) {
                finalList.add(
                    Alcohol(
                        rootView.context.getString(R.string.energy_drink),
                        left,
                        rootView.context.getString(R.string.energy_drink)
                    )
                )
            }
        } else {
            amount = left / additionalAlcohols.toDouble()
            if (alcoholArray?.get(2) == 1) {
                finalList.add(
                    Alcohol(
                        rootView.context.getString(R.string.vodka),
                        amount,
                        rootView.context.getString(R.string.vodka)
                    )
                )
            }
            if (alcoholArray?.get(3) == 1) {
                finalList.add(
                    Alcohol(
                        rootView.context.getString(R.string.gin),
                        amount,
                        rootView.context.getString(R.string.gin)
                    )
                )
            }
            if (alcoholArray?.get(5) == 1) {
                finalList.add(
                    Alcohol(
                        rootView.context.getString(R.string.tequila),
                        amount,
                        rootView.context.getString(R.string.tequila)
                    )
                )
            }
            if (alcoholArray?.get(6) == 1) {
                finalList.add(
                    Alcohol(
                        rootView.context.getString(R.string.whiskey),
                        amount,
                        rootView.context.getString(R.string.whiskey)
                    )
                )
            }
            if (alcoholArray?.get(7) == 1) {
                finalList.add(
                    Alcohol(
                        rootView.context.getString(R.string.rum),
                        amount,
                        rootView.context.getString(R.string.rum)
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


    private fun attachViews() {
        recyclerView = rootView.findViewById(R.id.cauldron_make_recycler)
    }
}