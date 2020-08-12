package com.partos.gamebox.fragments.mafia

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.flashback.db.DataBaseHelper
import com.partos.gamebox.MyApp
import com.partos.gamebox.R
import com.partos.gamebox.activities.MafiaActivity
import com.partos.gamebox.models.Action
import com.partos.gamebox.models.Player
import com.partos.gamebox.recycler.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "size"
private const val ARG_PARAM2 = "isStatic"

/**
 * A simple [Fragment] subclass.
 * Use the [MafiaGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MafiaGameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var size: Int? = null
    private var isStatic: Boolean? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var dayPanel: RecyclerView
    private lateinit var nightPanel: ConstraintLayout
    private lateinit var changePanelButton: Button
    private lateinit var changePanelButton2: Button
    private lateinit var nightPanelRoles: RecyclerView
    private lateinit var nightPanelActions: RecyclerView
    private lateinit var actionsPanel: RecyclerView
    private lateinit var changeLayout: LinearLayout
    private lateinit var changeYes: Button
    private lateinit var changeNo: Button
    private lateinit var endButton: ImageView
    private lateinit var questionText: TextView
    private lateinit var soundsImage: ImageView
    private lateinit var soundsLayout: ConstraintLayout
    private lateinit var soundsRecyclerView: RecyclerView
    private lateinit var soundPool: SoundPool
    private lateinit var soundArray: ArrayList<Int>
    private lateinit var mediaPlayerArray: ArrayList<MediaPlayer>

    private lateinit var playersList: ArrayList<Player>
    private lateinit var nightRoles: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            size = it.getInt(ARG_PARAM1)
            isStatic = it.getBoolean(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_mafia_game, container, false)
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
        @JvmStatic
        fun newInstance() =
            MafiaGameFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initFragment() {
        val db = DataBaseHelper(rootView.context)
        playersList = db.getPlayersList()
        MyApp.round = db.getMafiaRound()[0]

        attachViews()
        assignNightRoles()
        initSounds()

        val dayLayoutManager = LinearLayoutManager(this.context)
        val nPActionLayoutManager = LinearLayoutManager(this.context)
        val nPRolesLayoutManager = LinearLayoutManager(this.context)
        val actionsLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        dayPanel.layoutManager = dayLayoutManager
        dayPanel.addItemDecoration(MarginItemDecoration(12))
        dayPanel.adapter = DayPanelRecyclerViewAdapter(playersList)

        nightPanelActions.layoutManager = nPActionLayoutManager
        nightPanelActions.addItemDecoration(MarginItemDecoration(12))
        nightPanelRoles.layoutManager = nPRolesLayoutManager
        nightPanelRoles.addItemDecoration(MarginItemDecoration(12))

        actionsPanel.layoutManager = actionsLayoutManager
        actionsPanel.addItemDecoration(MarginItemDecorationHorizontal(12))
        if (db.getActionList().size != 0) {
            actionsPanel.adapter = ActionsPanelRecyclerViewAdapter(getReversedActionList())
        }

        changePanelButton.setOnClickListener {
            actionsPanel.visibility = View.GONE
            changeLayout.visibility = View.VISIBLE
            questionText.setText(rootView.context.getText(R.string.daytime_change))
            changeYes.setOnClickListener {
                changePanelButton.visibility = View.GONE
                changePanelButton2.visibility = View.VISIBLE
                dayPanel.visibility = View.GONE
                nightPanel.visibility = View.VISIBLE
                playersList = db.getPlayersList()
                nightPanelRoles.adapter = NightPanelRolesRecyclerViewAdapter(playersList)
                nightPanelActions.adapter = NightPanelActionsRecyclerViewAdapter(nightRoles)
                actionsPanel.visibility = View.VISIBLE
                changeLayout.visibility = View.GONE
                updateActions()
                if (db.getActionList().size != 0) {
                    actionsPanel.adapter = ActionsPanelRecyclerViewAdapter(getReversedActionList())
                }
                MyApp.currentActionList.clear()
            }
            changeNo.setOnClickListener {
                actionsPanel.visibility = View.VISIBLE
                changeLayout.visibility = View.GONE
            }
        }
        changePanelButton2.setOnClickListener {
            actionsPanel.visibility = View.GONE
            changeLayout.visibility = View.VISIBLE
            questionText.setText(rootView.context.getText(R.string.daytime_change))

            changeYes.setOnClickListener {
                MyApp.nightEnd = true
                changePanelButton.visibility = View.VISIBLE
                changePanelButton2.visibility = View.GONE
                dayPanel.visibility = View.VISIBLE
                nightPanel.visibility = View.GONE
                playersList = db.getPlayersList()
                dayPanel.adapter = DayPanelRecyclerViewAdapter(playersList)
                hideKeyboard()
                actionsPanel.visibility = View.VISIBLE
                changeLayout.visibility = View.GONE
                Handler().postDelayed({
                    MyApp.round.number++
                    db.updateMafiaRound(MyApp.round)
                    updateActions()
                    if (db.getActionList().size != 0) {
                        actionsPanel.adapter =
                            ActionsPanelRecyclerViewAdapter(getReversedActionList())
                    }
                    MyApp.currentActionList.clear()
                }, 200)
            }
            changeNo.setOnClickListener {
                actionsPanel.visibility = View.VISIBLE
                changeLayout.visibility = View.GONE
            }
        }

        endButton.setOnClickListener {
            actionsPanel.visibility = View.GONE
            changeLayout.visibility = View.VISIBLE
            questionText.setText(rootView.context.getText(R.string.mafia_end))
            changeYes.setOnClickListener {
                endGame()
                (rootView.context as MafiaActivity).finish()
            }
            changeNo.setOnClickListener {
                actionsPanel.visibility = View.VISIBLE
                changeLayout.visibility = View.GONE
            }
        }

        soundsImage.setOnClickListener {
            if (soundsLayout.visibility == View.GONE) {
                soundsLayout.visibility = View.VISIBLE
                val soundsLayoutManager = LinearLayoutManager(this.context)
                soundsRecyclerView.layoutManager = soundsLayoutManager
                soundsRecyclerView.addItemDecoration(MarginItemDecoration(12))
                soundsRecyclerView.adapter = SoundsRecyclerViewAdapter(soundPool, soundArray)
                soundsLayout.bringToFront()
            } else {
                soundsLayout.visibility = View.GONE
            }
        }
    }

    private fun initSounds() {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(4)
            .setAudioAttributes(audioAttributes)
            .build()
        soundArray = ArrayList()
        soundArray.add(soundPool.load(context, R.raw.announcment, 1))
        soundArray.add(soundPool.load(context, R.raw.end_of_game_city, 1))
        soundArray.add(soundPool.load(context, R.raw.end_of_game_mafia, 1))
        soundArray.add(soundPool.load(context, R.raw.miasto_budzi_sie, 1))
        soundArray.add(soundPool.load(context, R.raw.death, 1))
        soundArray.add(soundPool.load(context, R.raw.death2, 1))
        soundArray.add(soundPool.load(context, R.raw.gong, 1))
        soundArray.add(soundPool.load(context, R.raw.scream_woman, 1))
//        soundArray.add(soundPool.load(context, R.raw.orchestra_epic, 1))
//        soundArray.add(soundPool.load(context, R.raw.orchestra_normal, 1))
//        soundArray.add(soundPool.load(context, R.raw.orchestra_sad, 1))

    }

    private fun endGame() {
        val db = DataBaseHelper(rootView.context)
        val players = db.getPlayersList()
        for (player in players) {
            db.deletePlayer(player.id)
        }
        val actions = db.getActionList()
        for (action in actions) {
            db.deleteAction(action)
        }
        val round = db.getMafiaRound()[0]
        db.deleteMafiaRound(round)
    }

    private fun getReversedActionList(): ArrayList<Action> {
        val db = DataBaseHelper(rootView.context)
        if (db.getActionList().size == 1) {
            return db.getActionList()
        } else {
            val actions = db.getActionList()
            val reversedActions = actions.reversed()
            return reversedActions as ArrayList<Action>
        }
    }

    private fun updateActions() {
        val db = DataBaseHelper(rootView.context)
        for (action in MyApp.currentActionList) {
            db.addAction(action)
        }
    }

    private fun assignNightRoles() {
        nightRoles = ArrayList()
        for (i in playersList) {
            if (i.role == rootView.context.getString(R.string.mafia_role_medic)) {
                nightRoles.add(rootView.context.getString(R.string.healed))
            }
            if (i.role == rootView.context.getString(R.string.mafia_role_courtesan)) {
                nightRoles.add(rootView.context.getString(R.string.hooked_up))
            }
        }
        nightRoles.add(rootView.context.getString(R.string.killed))
    }


    private fun attachViews() {
        dayPanel = rootView.findViewById(R.id.mafia_game_day_panel)
        nightPanel = rootView.findViewById(R.id.mafia_game_night_panel)
        changePanelButton = rootView.findViewById(R.id.mafia_game_button_change_panel)
        changePanelButton2 = rootView.findViewById(R.id.mafia_game_button_change_panel2)
        nightPanelActions = rootView.findViewById(R.id.mafia_game_night_panel_actions)
        nightPanelRoles = rootView.findViewById(R.id.mafia_game_night_panel_roles)
        actionsPanel = rootView.findViewById(R.id.mafia_game_actions_recycler_view)
        changeLayout = rootView.findViewById(R.id.mafia_game_change_layout)
        changeYes = rootView.findViewById(R.id.mafia_game_change_button_yes)
        changeNo = rootView.findViewById(R.id.mafia_game_change_button_no)
        endButton = rootView.findViewById(R.id.mafia_game_end_image)
        questionText = rootView.findViewById(R.id.mafia_game_question_text)
        soundsImage = rootView.findViewById(R.id.mafia_game_sounds_image)
        soundsLayout = rootView.findViewById(R.id.mafia_game_sound_layout)
        soundsRecyclerView = rootView.findViewById(R.id.mafia_game_sounds_recycler)
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val inputManager =
                rootView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}