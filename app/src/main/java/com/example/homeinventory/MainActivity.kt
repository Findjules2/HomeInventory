package com.example.homeinventory

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var data = ArrayList<String>()
    private var data1 = ArrayList<String>()
    private var data2 = ArrayList<String>()
    private var data3 = ArrayList<String>()

//    private var table = TableLayout(baseContext)

    lateinit var ed1: EditText
    lateinit var ed2: EditText
    lateinit var ed3: EditText
    lateinit var ed4: EditText
    lateinit var ed5: EditText
    lateinit var ed6: EditText

    lateinit var b1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ed1 = findViewById(R.id.ed1)
        ed2 = findViewById(R.id.ed2)
        ed3 = findViewById(R.id.ed3)
        ed4 = findViewById(R.id.txtsub)
        ed5 = findViewById(R.id.txtpay)
        ed6 = findViewById(R.id.txtbal)
        b1 = findViewById(R.id.btn1)

        ed5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }
            override fun afterTextChanged(s: Editable?) {
                val subtotal = ed4.text.toString().toIntOrNull() ?: 0
                val pay = ed5.text.toString().toIntOrNull() ?: 0
                val bal = pay - subtotal
                ed6.setText(bal.toString())
            }
        })


        b1.setOnClickListener {
            add()
        }


    }
    fun add() {

        val prodname = ed1.text.toString()
        val price = ed2.text.toString().toInt()
        val qty = ed3.text.toString().toInt()
        val tot = price * qty

        data.add(prodname)
        data1.add(price.toString())
        data2.add(qty.toString())
        data3.add(tot.toString())

        val table = findViewById<TableLayout>(R.id.tb1)
        val row = TableRow(this)
        val t1 = TextView(this)
        val t2 = TextView(this)
        val t3 = TextView(this)
        val t4 = TextView(this)

        var total: String
        var sum = 0

        for (i in data.indices) {
            val pname = data[i]
            val prc = data1[i]
            val qtyy = data2[i]
            total = data3[i]

            t1.text = pname
            t2.text = prc
            t3.text = qtyy
            t4.text = total

            sum += total.toInt()
        }

        row.addView(t1)
        row.addView(t2)
        row.addView(t3)
        row.addView(t4)
        table.addView(row)

        ed4.setText(sum.toString())
        ed1.text.clear()
        ed2.text.clear()
        ed3.text.clear()
        ed1.requestFocus()

    }

}
