package com.example.newspyfall
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddUserDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Create the dialog builder
            val builder = AlertDialog.Builder(it)

            // Inflate the layout for the dialog view
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.add_user_popup, null)

            // Get the EditText view and set an onClickListener to the add user button
            val userNameInput = view.findViewById<EditText>(R.id.user_name_input)
            val addUserButton = view.findViewById<Button>(R.id.add_user_button)
            addUserButton.setOnClickListener {
                val name = userNameInput.text.toString()
                (activity as MainActivity).addUser(name)
                dismiss()
            }

            // Set the dialog view and return the dialog
            builder.setView(view)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

