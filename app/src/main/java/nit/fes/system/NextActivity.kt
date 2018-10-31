package nit.fes.system

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import org.jetbrains.anko.*
import android.content.Intent
import nit.fes.system.R


class NextActivity : AppCompatActivity() {

    private var count = 1
    private val price = 400
    private val name = "お好み焼き"

    private fun CountUp(){
        count++
    }
    private fun CountDown(){
        count--
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        verticalLayout{
            gravity = Gravity.CENTER
            textView{
                gravity = Gravity.CENTER
                text = "${name}"
                textSize = 40f
                textColor = Color.BLACK
                backgroundColor = Color.RED
            }

            textView{
                gravity = Gravity.CENTER
                text = "${price}円"
                textSize = 40f
                textColor = Color.BLACK
                backgroundColor = Color.RED
            }

            button{
                gravity = Gravity.CENTER
                text = "+"
                textSize = 60f
                backgroundColor = Color.CYAN
                onClick{
                            CountUp()
                            toast("${count}個")
                }
            }

            button{
                gravity = Gravity.CENTER
                text = "-"
                textSize = 60f
                backgroundColor = Color.GREEN
                onClick{
                            CountDown()
                            toast("${count}個")
                }
            }


            button{
                text = "Pay"
                onClick{
                        toast("決済金額は ${count * price} 円です")
                        count = 1


                }
            }
            button{
                text = "QRcodeReader"
                onClick{
                    val intent = Intent(this@NextActivity, QrCodeReadInViewActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }


    private fun setTextView(){

    }

}