package lopez.mario.clicker

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    var cuenta: Int = 0
    var cosa: String? ="Contador"
    lateinit var bSum: Button
    lateinit var tvContador: TextView
    lateinit var bRes: Button
    lateinit var bBorrar: Button
    lateinit var etContando: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bSum = findViewById(R.id.b_sum)
        tvContador = findViewById(R.id.tv_contador)
        bRes = findViewById(R.id.b_res)
        bBorrar = findViewById(R.id.b_borrar)
        etContando  = findViewById(R.id.et_cosa)

        bSum.setOnClickListener{
            cuenta++
            tvContador.setText("$cuenta")
        }

        bRes.setOnClickListener{
            cuenta--
            tvContador.setText("$cuenta")
        }

        bBorrar.setOnClickListener{
            borrar()
        }

    }

    fun borrar(){
        val alertDialog: AlertDialog?= this?.let{
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        cuenta = 0
                        tvContador.setText("$cuenta")
                    })
                setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
            }
            builder?.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)

            builder.create()
        }
        alertDialog?.show()
    }

    override fun onStop() {
        super.onStop()
        val pref = this.getPreferences(Context.MODE_PRIVATE)
        cosa = etContando.text.toString()
        val edit = pref.edit()
        edit.putInt("key_cuenta",cuenta)
        edit.putString("cosa",cosa)
        edit.apply()
    }

    override fun onStart() {
        super.onStart()
        val pref = this.getPreferences(Context.MODE_PRIVATE)
        cuenta = pref.getInt("key_cuenta",0)
        tvContador.setText("$cuenta")
        cosa = pref.getString("cosa","Contador")

        etContando.setText(cosa)
    }
}