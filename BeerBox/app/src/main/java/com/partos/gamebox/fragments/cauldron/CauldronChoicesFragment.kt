package com.partos.gamebox.fragments.cauldron

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.partos.gamebox.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CauldronHowToMakeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CauldronChoicesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var rootView: View
    private lateinit var alcoholsList: ArrayList<Int>

    private lateinit var litresLayout: LinearLayout
    private lateinit var alcoholsLayout: LinearLayout
    private lateinit var litresEditText: EditText
    private lateinit var beerCard: CardView
    private lateinit var wineCard: CardView
    private lateinit var vodkaCard: CardView
    private lateinit var tequilaCard: CardView
    private lateinit var whiskeyCard: CardView
    private lateinit var champagneCard: CardView
    private lateinit var rumCard: CardView
    private lateinit var ginCard: CardView
    private lateinit var juiceCard: CardView
    private lateinit var energyCard: CardView
    private lateinit var nextButton: Button
    private lateinit var makeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cauldron_choices, container, false)
        initFragment()
        return rootView
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CauldronHowToMakeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CauldronChoicesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun initFragment() {
        attachViews()
        initList()
        attachListeners()
    }

    private fun initList() {
        alcoholsList = ArrayList()
        for (i in 0 until 10) {
            alcoholsList.add(0)
        }
    }

    private fun attachListeners() {
        nextButton.setOnClickListener {
            if (litresEditText.text.toString() != "") {
                if (litresEditText.text.toString().toInt() > 2) {
                    litresLayout.visibility = View.GONE
                    alcoholsLayout.visibility = View.VISIBLE
                    hideKeyboard(litresEditText)
                } else {
                    Toast.makeText(
                        rootView.context,
                        rootView.context.getText(R.string.toast_too_little_litres),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    rootView.context,
                    rootView.context.getText(R.string.toast_give_litres),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        makeButton.setOnClickListener {
            if (isAnyChecked()) {
                val fragment = CauldronMakeFragment.newInstance(
                    alcoholsList,
                    litresEditText.text.toString().toInt()
                )
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left
                    )
                    ?.replace(R.id.cauldron_frame_layout, fragment)
                    ?.addToBackStack(CauldronMakeFragment.toString())
                    ?.commit()
            } else {
                Toast.makeText(
                    rootView.context,
                    rootView.context.getText(R.string.toast_no_alcohols_chosen),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        beerCard.setOnClickListener {
            if (beerCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                beerCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[0] = 1
            } else {
                beerCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[0] = 0
            }
        }
        wineCard.setOnClickListener {
            if (wineCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                wineCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[1] = 1
            } else {
                wineCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[1] = 0
            }
        }
        vodkaCard.setOnClickListener {
            if (vodkaCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                vodkaCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[2] = 1
            } else {
                vodkaCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[2] = 0
            }
        }
        ginCard.setOnClickListener {
            if (ginCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                ginCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[3] = 1
            } else {
                ginCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[3] = 0
            }
        }
        champagneCard.setOnClickListener {
            if (champagneCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                champagneCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[4] = 1
            } else {
                champagneCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[4] = 0
            }
        }
        tequilaCard.setOnClickListener {
            if (tequilaCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                tequilaCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[5] = 1
            } else {
                tequilaCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[5] = 0
            }
        }
        whiskeyCard.setOnClickListener {
            if (whiskeyCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                whiskeyCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[6] = 1
            } else {
                whiskeyCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[6] = 0
            }
        }
        rumCard.setOnClickListener {
            if (rumCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                rumCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[7] = 1
            } else {
                rumCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[7] = 0
            }
        }
        juiceCard.setOnClickListener {
            if (juiceCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                juiceCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[8] = 1
            } else {
                juiceCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[8] = 0
            }
        }
        energyCard.setOnClickListener {
            if (energyCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurpleLightLight)) {
                energyCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurple))
                alcoholsList[9] = 1
            } else {
                energyCard.setCardBackgroundColor(rootView.context.getColorStateList(R.color.colorPurpleLightLight))
                alcoholsList[9] = 0
            }
        }
    }

    private fun isAnyChecked(): Boolean {
        when {
            beerCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            wineCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            vodkaCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            rumCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            tequilaCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            whiskeyCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            ginCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            champagneCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            juiceCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            energyCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple) -> {
                return true
            }
            else -> return false
        }
    }

    private fun attachViews() {
        litresLayout = rootView.findViewById(R.id.cauldron_choice_linear_litres)
        alcoholsLayout = rootView.findViewById(R.id.cauldron_choice_linear_alcohols)
        litresEditText = rootView.findViewById(R.id.cauldron_choice_litres_edit)
        beerCard = rootView.findViewById(R.id.cauldron_choice_card_beer)
        wineCard = rootView.findViewById(R.id.cauldron_choice_card_wine)
        vodkaCard = rootView.findViewById(R.id.cauldron_choice_card_vodka)
        tequilaCard = rootView.findViewById(R.id.cauldron_choice_card_tequila)
        rumCard = rootView.findViewById(R.id.cauldron_choice_card_rum)
        whiskeyCard = rootView.findViewById(R.id.cauldron_choice_card_whiskey)
        ginCard = rootView.findViewById(R.id.cauldron_choice_card_gin)
        champagneCard = rootView.findViewById(R.id.cauldron_choice_card_champagne)
        juiceCard = rootView.findViewById(R.id.cauldron_choice_card_juice)
        energyCard = rootView.findViewById(R.id.cauldron_choice_card_energy_drink)
        nextButton = rootView.findViewById(R.id.cauldron_choice_button_next)
        makeButton = rootView.findViewById(R.id.cauldron_choice_button_make)
    }

    private fun hideKeyboard(view: View) {
        val inputManager =
            rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}