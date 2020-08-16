package com.partos.gamebox.logic.cauldron.listeners

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.R
import com.partos.gamebox.activities.MainActivity
import com.partos.gamebox.models.Cauldron

class CauldronSavedListeners {

    private lateinit var nameText: TextView
    private lateinit var nameEdit: EditText
    private lateinit var editLayout: LinearLayout
    private lateinit var normalLayout: LinearLayout
    private lateinit var editCard: CardView
    private lateinit var saveCard: CardView
    private lateinit var deleteCard: CardView
    private lateinit var db: DataBaseHelper
    private lateinit var cauldron: Cauldron
    private lateinit var deleteLayout: LinearLayout
    private lateinit var deleteYes: CardView
    private lateinit var deleteNo: CardView

    fun initListeners(rootView: View, cauldronId: Long) {
        db = DataBaseHelper(rootView.context)
        cauldron = db.getCauldron(cauldronId)
        attachViews(rootView)
        attachListeners(rootView)
    }

    private fun attachListeners(rootView: View) {
        editCard.setOnClickListener {
            nameText.visibility = View.GONE
            nameEdit.visibility = View.VISIBLE
            nameEdit.setText(nameText.text)
            normalLayout.visibility = View.GONE
            editLayout.visibility = View.VISIBLE
        }
        saveCard.setOnClickListener {
            cauldron.name = nameEdit.text.toString()
            db.updateCauldron(cauldron)
            nameText.text = nameEdit.text
            nameEdit.visibility = View.GONE
            nameText.visibility = View.VISIBLE
            normalLayout.visibility = View.VISIBLE
            editLayout.visibility = View.GONE
            hideKeyboard(rootView)
        }
        deleteCard.setOnClickListener {
            normalLayout.visibility = View.GONE
            nameText.visibility = View.GONE
            deleteLayout.visibility = View.VISIBLE
        }
        deleteYes.setOnClickListener {
            val alcohols = db.getAlcoholList(cauldron.id)
            for (alcohol in alcohols) {
                db.deleteAlcohol(alcohol)
            }
            db.deleteCauldron(cauldron)
            (rootView.context as MainActivity).supportFragmentManager
                .popBackStack()
        }
        deleteNo.setOnClickListener {
            normalLayout.visibility = View.VISIBLE
            nameText.visibility = View.VISIBLE
            deleteLayout.visibility = View.GONE
        }
    }

    private fun attachViews(rootView: View) {
        nameText = rootView.findViewById(R.id.cauldron_saved_text)
        nameEdit = rootView.findViewById(R.id.cauldron_saved_edit)
        editLayout = rootView.findViewById(R.id.cauldron_saved_linear_edit)
        normalLayout = rootView.findViewById(R.id.cauldron_saved_linear_normal)
        editCard = rootView.findViewById(R.id.cauldron_saved_edit_card)
        saveCard = rootView.findViewById(R.id.cauldron_saved_save_card)
        deleteCard = rootView.findViewById(R.id.cauldron_saved_delete_card)
        deleteLayout = rootView.findViewById(R.id.cauldron_saved_linear_question)
        deleteYes = rootView.findViewById(R.id.cauldron_saved_question_yes)
        deleteNo = rootView.findViewById(R.id.cauldron_saved_question_no)
    }

    private fun hideKeyboard(rootView: View) {
        val view = (rootView.context as MainActivity).currentFocus
        if (view != null) {
            val inputManager =
                rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}