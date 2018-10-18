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
    data class Arguments(val id: Int, val text: String, val yesButton: String, val noButton: String, val bundle: Bundle? = null) : Parcelable

    var dialogID:Int = 0
    var bundle: Bundle? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dialog_two_buttons_ft, container, false)
        val title = v.findViewById<TextView>(R.id.tv_team1)
        val yes = v.findViewById<Button>(R.id.dialog_button_yes)
        val no = v.findViewById<Button>(R.id.dialog_button_no)

        arguments?.getParcelable<Arguments>(ARGS)?.let {
            title.text = it.text
            yes.text = it.yesButton as String
            yes.setOnClickListener{_ -> notify(SELECTED_YES)}
            no.text = it.noButton as String
            no.setOnClickListener{_ ->
                notify(SELECTED_NO)
                dismiss()
            }
            dialogID = it.id
            bundle = it.bundle
        }
        return v
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        notify(SELECTED_DISMISS)
    }

    interface onDialogClickedListener {
        fun onClick(id: Int, selectedChoice: Int, bundle: Bundle?)
    }

    private fun notify(state: Int){
        val fragment = targetFragment
        if (fragment is onDialogClickedListener) {
            (fragment as onDialogClickedListener).onClick(dialogID, state, bundle)
        }
    }
}