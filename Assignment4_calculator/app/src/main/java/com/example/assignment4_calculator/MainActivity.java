package com.example.assignment4_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends Activity implements View.OnClickListener{
    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       resultTv = findViewById(R.id.result_tv);
       solutionTv = findViewById(R.id.solution_tv);

       assignID(buttonC,R.id.button_c);
       assignID(buttonBrackOpen,R.id.button_open_bracket);
       assignID(buttonBrackClose,R.id.button_closed_bracket);
       assignID(buttonDivide,R.id.button_divide);
       assignID(buttonMultiply,R.id.button_multiply);
       assignID(buttonPlus,R.id.button_plus);
       assignID(buttonMinus,R.id.button_minus);
       assignID(buttonEqual,R.id.button_equal);
       assignID(button0,R.id.button_0);
       assignID(button1,R.id.button_1);
       assignID(button2,R.id.button_2);
       assignID(button3,R.id.button_3);
       assignID(button4,R.id.button_4);
       assignID(button5,R.id.button_5);
       assignID(button5,R.id.button_5);
       assignID(button6,R.id.button_6);
       assignID(button7,R.id.button_7);
       assignID(button8,R.id.button_8);
       assignID(button9,R.id.button_9);
       assignID(button0,R.id.button_0);
       assignID(buttonAC,R.id.button_ac);
       assignID(buttonPercent,R.id.button_percent);

    }

    void assignID(MaterialButton btn,int id) {
       btn = findViewById(id);
       btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dateToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dateToCalculate = dateToCalculate.substring(0,dateToCalculate.length()-1);
        } else {
            dateToCalculate = dateToCalculate + buttonText;
        }
        solutionTv.setText(dateToCalculate);

        String finalResult = getResult(dateToCalculate);

        if(!finalResult.equals("Error")) {
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e) {
            return "Error";
        }
    }

}