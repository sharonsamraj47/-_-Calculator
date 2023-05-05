package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution,result;
    MaterialButton Button_c,Button_open,Button_close,Button_divide;
    MaterialButton Button_7,Button_8,Button_9,Button_multi;
    MaterialButton Button_4,Button_5,Button_6,Button_sub;
    MaterialButton Button_1,Button_2,Button_3,Button_add;
    MaterialButton Button_ac,Button_0,Button_dot,Button_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solution = findViewById(R.id.solution);
        result = findViewById(R.id.result);

        assignId(Button_c,R.id.Button_c);
        assignId(Button_open,R.id.Button_open);
        assignId(Button_close,R.id.Button_close);
        assignId(Button_divide,R.id.Button_divide);
        assignId(Button_7,R.id.Button_7);
        assignId(Button_8,R.id.Button_8);
        assignId(Button_9,R.id.Button_9);
        assignId(Button_multi,R.id.Button_multi);
        assignId(Button_4,R.id.Button_4);
        assignId(Button_5,R.id.Button_5);
        assignId(Button_6,R.id.Button_6);
        assignId(Button_sub,R.id.Button_sub);
        assignId(Button_1,R.id.Button_1);
        assignId(Button_2,R.id.Button_2);
        assignId(Button_3,R.id.Button_3);
        assignId(Button_add,R.id.Button_add);
        assignId(Button_ac,R.id.Button_ac);
        assignId(Button_0,R.id.Button_0);
        assignId(Button_dot,R.id.Button_dot);
        assignId(Button_equal,R.id.Button_equal);




    }
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button =(MaterialButton) view;
        String buttontext = button.getText().toString();
        String dtoc = solution.getText().toString();

        if(buttontext.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttontext.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttontext.equals("C")){
            dtoc = dtoc.substring(0,dtoc.length()-1);

        }
        else{
            dtoc = dtoc+buttontext;
        }

        solution.setText(dtoc);

        String finalresult = getresult(dtoc);

        if(!finalresult.equals("Err")){
            result.setText(finalresult);
        }

    }
    String getresult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if(finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;
        }
        catch(Exception e){
                return "Err";
        }
    }
}