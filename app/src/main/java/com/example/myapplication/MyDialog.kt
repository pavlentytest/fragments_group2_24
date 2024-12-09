package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("Какое-то сообщение!")
            .setPositiveButton("Ok"){ _,_ -> }
            .setNegativeButton("Cancel"){ _,_ -> }
            .create()
    }

    companion object {
        const val TAG = "MyDialog"
    }

}