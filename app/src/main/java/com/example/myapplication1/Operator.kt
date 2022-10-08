package com.example.myapplication1

import android.util.Log
import java.util.*
import kotlin.math.abs

var question: Queue<String> = LinkedList<String>()
class Operator {

    //My program created the problems by identifying the operators and creating random numbers as needed

    var operator: Queue<String> = LinkedList<String>()//queue: save all operators for efficiency
    var displayt:String = "" //String: the problem to be displayed
    var size: Int = 0//variable: number of operators, to calculate size of problem(2,3,4)
    var result: Int =0;// variable: result of problem
    var loop:Int = 1;//variable index:to show location where number operators and  parenthesis has to be added



    fun addoper(data1: String){//functiom to be called to add operator
        operator.add(data1)//add operator
    }

    fun addstring(i : Int): Int{//func adding all (problem) numbers to string
        displayt += i.toString()
        return i
    }
    private fun opadder(string :String):Int{//func adding all (problem) operators to string
        displayt+=string
        return 0
        //to fill
    }

    fun calculator(){// func called to start process of generating problem

        if (size==3 ) {displayt= "(("+ displayt}//add 2 at beginning parenthesis if there are 4 numbers(3 operators)
        else if (size==2){displayt= "("+ displayt}//add parenthesis at beginning if there are 3 numbers(2 operators)


        problemMaker(true)//function running once to make the first (2 number) operator and numb
        for(y in 1..size-1){//function running until end to fill all the rest number and operators
            //and calculating there total results
            problemMaker(false)

        }

    }
    private fun problemMaker(x : Boolean){
        var div: Int = 0 //special variable for division as we need to generate specific random number
        var div1: Int = 0

        if(x ==true){//if passed boolean parameter true we generate 2 numbers and the result of the 2 variables according
        if (operator.peek() == "+"){

            //using random func to get random numbers from range 1 to 20
            result= addstring(random(20))+ opadder("+")+addstring(random(20))

            //ir order to add the operator sign while performing it in one line i have made the
            //function opadder which adds the operators and returns the value which is added to
            //result and makes no changes. Wihtout the opadder i would have had to create each
            //variables and addition to string individually and later perform another operation
            //to caulate the value of 2 given number and add to result, opadd makes it efficent

        }
        else if (operator.peek() == "-"){
            //if first value from the randomly generated queue of operator is x then do:
            result= addstring(random(20))+ opadder("-")- addstring(random(20))
            //explanation above

        }
        else if (operator.peek() == "*"){
            //explanation above
            result= (addstring(random(20))+ opadder("x"))* addstring(random(20))

        }
        else if (operator.peek() == "/"){
            div = (random(20))//generating first rnandom number for division

            var factors2 = ((1..20).filter { div % it == 0 }).shuffled().first()
            // as division is the only thing that can create fractional number from whole number
            // we just get the factors of the first number provide problems with only whole number
            //solutions

            //factors generates a random list of factors divisible by the first generated number
            //first numbers are generated from 1 to 20 in a list (there can be more factors but we
            //we only need to display number between 1 and 20, after Filter is used to filter
            //and change the array with numbers with reminder only 0 compared to div.
            //this are then shuffled so they are randomly selected and the first variable is picked
            result= (addstring(div)+ opadder("/"))/ addstring(factors2)

            //first value of result equals to first number generated divided by its random factor

        }}
        else if(x ==false){
            //if given paramter is false: means we  need to generated only 1 number the second one
            //as first number is the calculated result of the first 2 elements (in Boolean = true)

            if (operator.peek() == "+"){
                displayt+="+"
                result+= addstring(random(20))
                // we add that random number to previous result
            }
            else if (operator.peek() == "-"){
                displayt+="-"
                result-= addstring(random(20))
                // we minus that random number to previous result

            }
            else if (operator.peek() == "*"){
                displayt+="x"
                result*= addstring(random(20))
                // we times that random number to previous result
            }
            else if (operator.peek() == "/"){
        //in this case we create factors from 1 to 20 of the result to have whole number solutions
                var factors = ((1..(20)).filter { abs(result) % it == 0 }).shuffled().first()
                //factors generating explanation line80
                displayt+="/"//add operator to string for display
                result/= addstring(factors)
                //we divide the created factors by solution


            }}
        if ((size==3 && loop==1)|| (size==3 && loop==2)){
        //here the program adds the closing brackets in the loop, it checks which position its at
        //through variable loop, it adds 2 parenthesis if there are 3 operators(4 number)
        //variable loop specicifies the positions the loop is currently running at

            displayt+=")"//adds last closing braket

        }
        else if (size==2 && loop==1){// if 2 operators (3 number adds only 1 closing bracket before
            //adding the first randomnumber, operator and another random number in (Boolean=true)
            displayt+=(")")//ads first closing braket
        }
        loop+=1 //loop is increased by 1 to keep track of position
        operator.remove()// first operator gets removed from queue for program to caculate next
        //operator values and results

    }


    private fun random(range:Int): Int {
        //function geenrating random number given the range as parameter
        var randomInt = (1..range).shuffled().first()
        //numbers are generated and added to list
        return randomInt
    }

}