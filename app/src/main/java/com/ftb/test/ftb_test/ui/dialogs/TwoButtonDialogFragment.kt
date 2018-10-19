package com.ftb.test.ftb_test.ui.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.extra.extraKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.dialog_two_buttons_ft.*
import kotlinx.android.synthetic.main.dialog_two_buttons_ft.view.*


class TwoButtonDialogFragment : DialogFragment() {

/*
 * При использовании в вызывающем фрагменте необхожимо установить dialog.setTargetFragment(this) && :onDialogClickedListener
 */

    companion object {
        val SELECTED_YES = 0
        val SELECTED_NO = 1
        val SELECTED_DISMISS = 2
        val ARGS by extraKey()
        fun newInstance(args: Arguments): TwoButtonDialogFragment {
            val f = TwoButtonDialogFragment()

            val bundle = Bundle()
            bundle.putParcelable(ARGS, args);
            f.setArguments(bundle);
            return f;
        }
    }

    @Parcelize
    data class Arguments(val team1Name:String, val team2Name: String, val scoreTeam1: Int, val scoreTeam2: Int) : Parcelable

    lateinit var btTeam1Plus : Button
    lateinit var btTeam2Plus : Button
    lateinit var btTeam1Minus : Button
    lateinit var btTeam2Minus : Button
    lateinit var score1View : TextView
    lateinit var score2View : TextView
    var score1: Int = 0
    var score2: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dialog_two_buttons_ft, container, false)
        val team1View = v.findViewById<TextView>(R.id.tv_team1)
        val team2View = v.findViewById<TextView>(R.id.tv_team2)
        val yes = v.findViewById<Button>(R.id.dialog_button_yes)
        val no = v.findViewById<Button>(R.id.dialog_button_no)
        score1View = v.findViewById<Button>(R.id.tv_score1)
        score2View = v.findViewById<Button>(R.id.tv_score2)
        v.findViewById<Button>(R.id.bt_team1_plus).setOnClickListener { _ -> score1Update(1)}
        v.findViewById<Button>(R.id.bt_team1_minus).setOnClickListener { _ -> score1Update(-1)}
        v.findViewById<Button>(R.id.bt_team2_plus).setOnClickListener { _ -> score2Update(1)}
        v.findViewById<Button>(R.id.bt_team2_minus).setOnClickListener { _ -> score2Update(-1)}
        arguments?.getParcelable<Arguments>(ARGS)?.let {
            team1View.text = it.team1Name
            team2View.text = it.team2Name
            yes.text = "Yes"
            yes.setOnClickListener{_ -> notify(SELECTED_YES)}
            no.text = "No "
            no.setOnClickListener{_ ->
                notify(SELECTED_NO)
                dismiss()
            }
            score1 = if(it.scoreTeam1 > 0) it.scoreTeam1 else 0
            score2 = if(it.scoreTeam2 > 0) it.scoreTeam2 else 0
            score1Update(0)
            score2Update(0)
        }
        return v
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        notify(SELECTED_DISMISS)
    }

    private fun score1Update(delta: Int){
        score1 = score1 + delta
        if (score1 < 0 ) score1 = 0
        score1View.text = score1.toString()
    }

    private fun score2Update(delta: Int){
        score2 = score2 + delta
        if (score2 < 0 ) score2 = 0
        score2View.text = score2.toString()
    }

    interface OnDialogClickedListener {
        fun onDialogChoiceClick(choice: Int, team1Name:String, team2Name: String, team1Score: Int, team2Score: Int)
    }

    private fun notify(state: Int){
        val fragment = targetFragment
        if (fragment is OnDialogClickedListener) {
            arguments?.getParcelable<Arguments>(ARGS)?.let {
                (fragment as OnDialogClickedListener).onDialogChoiceClick(state, it.team1Name, it.team2Name, score1, score2)
            }
        }
    }


}