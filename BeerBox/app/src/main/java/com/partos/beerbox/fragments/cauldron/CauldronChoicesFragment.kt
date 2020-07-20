package com.partos.beerbox.fragments.cauldron

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
import com.partos.beerbox.R

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
        attachListeners()
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
        beerCard.setOnClickListener {
            if (beerCard.cardBackgroundColor == rootView.context.getColorStateList(R.color.colorPurple))
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