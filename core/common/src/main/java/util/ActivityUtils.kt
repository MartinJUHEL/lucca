package util

import android.app.Activity
import android.content.Intent
import android.net.Uri

fun Activity.dialPhoneNumber(phoneNumber: String) {
    Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }.also(::startActivity)
}

fun Activity.sendEmail(recipient: String) {
    Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$recipient")
    }.also(::startActivity)
}