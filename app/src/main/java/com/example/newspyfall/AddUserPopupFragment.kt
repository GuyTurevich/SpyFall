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

            val userNameInput = view.findViewById<EditText>(R.id.user_name_input)
            val addUserButton = view.findViewById<Button>(R.id.add_user_button)
            val cancelButton = view.findViewById<Button>(R.id.cancel_button)

            val hebrewLettersRegex = Regex("[א-ת]+")

            addUserButton.setOnClickListener {
                val userName = userNameInput.text.toString().trim() // Remove leading and trailing whitespaces

                if (userName == "") { // will also catch a name with only whitespaces (trim)
                    userNameInput.error = "שם לא יכול להיות ריק"
                    return@setOnClickListener
                } else if (!userName.matches(hebrewLettersRegex)) {
                    userNameInput.error = "שם יכול להכיל רק אותיות בעברית"
                    return@setOnClickListener
                }

                (activity as MainActivity).addUser(userName)
                dismiss()
            }

            cancelButton.setOnClickListener {
                dismiss()
            }

            // Set the dialog view and return the dialog
            builder.setView(view)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

