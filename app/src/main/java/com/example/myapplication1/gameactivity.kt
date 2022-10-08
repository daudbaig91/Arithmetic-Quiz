package com.example.myapplication1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.gamefragment.*
import kotlinx.android.synthetic.main.summary.*


class gameactivity : AppCompatActivity() {
    var listop = mutableListOf<Operator>();//mutable list containing problem of type Operator()
    var addv = 0 //holds total problems
    var countadd = 0
    var correct = 0//variable: holds total right answer user got
    var incorrect = 0//variable: holds total wrong answer user got
    lateinit var timer: CountDownTimer //creating CountDownTimer variable
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gamefragment)

        timerdown(50)//running function to start timer at 50 sec


        newdata()//start function, generates random problems and display to user


        small.setOnClickListener(){
            submit("<")//run function submit with operator argument if button pressed

        }
        big.setOnClickListener(){

            submit(">")//run function submit with operator argument if button pressed

        }
        same.setOnClickListener(){
            submit("=")//run function submit with operator argument if button pressed

        }
        nextbttn.setOnClickListener(){
            // if button next(questio) is pressed hide button amd make option button clickable
            nextbttn.isVisible = false
            small.isClickable = true
            big.isClickable = true
            same.isClickable = true
            resultText.setText("        ")
            resultText.setBackgroundResource(R.drawable.roundbutton)
            //set background to default colors
            addv+=2 //ads 2 more (problems) to addv variable(total problems done)
            newdata() // runs new datata() again to generate 2 new problems and display them
        }

    }

    fun submit(choice: String){//function runs when user chooses a options from 3 button

        //if statments checks if user selection is true compared to the 2 problems shown
        //if choice is right it runs func answerdisplay() to show correct or incorrect to user
        if (listop.get(addv).result > listop.get(addv+1).result){
            if (choice==">"){

                answerdisplay(true)//if true means correct
            }
            else{
                answerdisplay(false)//if false means incorrect asnwer
            }
        }
        else if (listop.get(addv).result < listop.get(addv+1).result){
            if (choice=="<"){
                answerdisplay(true)
            }
            else{
                answerdisplay(false)
            }
        }
        else if (listop.get(addv).result == listop.get(addv+1).result){
            if (choice=="=="){
                answerdisplay(true)
            }
            else{
                answerdisplay(false)
            }
        }


    }

    fun displaytest() {
        //function displays summary at the end of game
        setContentView(R.layout.summary)
        //sets final layout to custom layout "summary"
        right.setText(correct.toString())
        //set total wrong answers in textview view ("right")
        not.setText(incorrect.toString())
        //set total wrong answers in textview view ("not")


    }

    fun timerdown(x :Int){//managing timer func
        val i = (counter.text.toString()).toInt()//gets current time in timer and coverts to int

        /* created a timer object using/from CountDowntimer library ,with starting position:
            x+i where x is the seconds where the counter starts at and i is the current time in
            counter. countdowninterval is -1 second
            the app is designed as such that if the game starts it passes 0 seconds and the default
            seconds in xml file is 50 therefore it starts at 50, but if user gets 5 answers right
            the program gets current time left in timer and cancels the timer, and  runs
            timerdown(x) again to run a new timer with the previous saved time (i) + x which is the
            value passed as argument which is 10 seconds adds 10 seconds to previous time and starts
            new timer*/
        timer = object : CountDownTimer(((x+i)*1000).toLong(), 1000) {
            /* created a timer object using/from CountDowntimer library ,with starting position:
            x+i where x is the seconds where the counter starts at and i is the current time in
            counter. countdowninterval is -1 second*/
            override fun onTick(millisUntilFinished: Long) {

                var seconds = (millisUntilFinished / 1000).toInt()
                counter.setText(seconds.toString())
                //on tick display each seconds down
            }
            override fun onFinish() {
                displaytest()
                //when timer fimished run displaytest() which shows summary layout at end
            }
        }.start()//starts timer
         }


    fun newdata(){//function creating 2 problems and showing to user
        listop.add(Operator())
        listop.add(Operator())//create to operator object and add them to public list

        var equation1:Operator=equationgenerator(listop.get(addv))
        var equation2:Operator = equationgenerator(listop.get(addv+1))
        //creates 2 variables for both problems, which run function equationgenerator()
        //which generate random problem



        p1.setText((equation1.displayt.toString()))
        p2.setText((equation2.displayt.toString()))
        //displays problems to user

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        //if user at any time either in game or summary presses back inbuilt button take him to menu
        startActivity(intent)
    }


    fun answerdisplay(x:Boolean){//fucntion displaying correct or incorrect answer

        nextbttn.isVisible = true
        small.isClickable = false
        big.isClickable = false
        same.isClickable = false
        //when users chooses a option from 3 buttons it diactivates them here, and shows next bttn
        // to go to next problem
        if(x==true){//if boolean true(right choice) display correct and change the back and
            //change text color background to green
            resultText.setText("Correct")
            resultText.setBackgroundResource(R.drawable.roundbutton3)//contains button green layout
            resultText.setTextColor(Color.parseColor("#26580F"));
            correct +=1//correct answer increases
            countadd+=1//correct answer for time increases

            if (countadd ==5){// if countadd reached 5 cancel timer and start timer with 10 sec more

                countadd=0//reset count to 0 for next right answers
                timer.cancel()
                timerdown(10)

            }

        }

        else{// if choice wrong or bool false,display incorrect and change color/background of text
            // red, increase incorrect answers
            resultText.setText("Incorrect")
            resultText.setBackgroundResource(R.drawable.roundbutton2)
            resultText.setTextColor(Color.parseColor("#BA0001"));
            incorrect+=1
        }

    }





    fun random(range:Int): Int {
        //function geenrating random number given the range as parameter
        var randomInt = (1..range).shuffled().first()//generating random number through shuffle

        return randomInt
    }


    fun equationgenerator(n : Operator):Operator{//equation generator generates random problems
        //takes an operator as parameter
        var listA = listOf<String>("+", "-", "*","/")//list of operators
        var x = random(3)//random number for number of numbers in problem
        // generating random number for lenght of problems, either 2,3,4 expressions
        n.size = x
        //set size in operator classes, x = leght of total expressions


        var operator:String=""//variable holding generated random operators

        for(y in 1..x){//lopping "x" times to add random list of operators with given length
            operator  = listA.shuffled().first()//shufflng list of operators to randomise pick
            //adding random genrated operators (also for calcualtions and display) to operator class
            n.addoper(operator)
        }

        n.calculator()//running function calculator in Operator() class to create random problems
        //and also display the generated texts, also calculate the values of each problem
        if (n.result > 100 || n.result < -100){
            //in order to fulfill the task result being under 100, the program if result is more
            //100, it re runs equationgenerator() function until it generated a random problem again
            // under 100

            equationgenerator(Operator())
        }
        // i have done it this way, as i tested it, and is still is really efficent, without no
        //lags for the user to even notice the wait for a generated problem under 100
        //i could have solved it whithout the loop, but it wouldnt have made a difference in
        //efficeny, it is unlikely that it displays +100 many time as teh ranges 1-20 are quite
        //low so for this situatuin this is still a efficent way
        return n
    }


}
