package com.jasson31.menuselector;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InputDialog extends Dialog {
    private TextView titleView, contentView;
    private EditText name, preference;
    private Button yesBtn, noBtn;
    private String title, content;
    private View.OnClickListener yesBtnListener, noBtnListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_take_restaurant);

        titleView = (TextView) findViewById(R.id.dialog_title);
        contentView = (TextView) findViewById(R.id.dialog_content);
        name = (EditText) findViewById(R.id.dialog_input_name);
        preference = (EditText) findViewById(R.id.dialog_input_preference);
        yesBtn = (Button) findViewById(R.id.dialog_btn_Yes);
        noBtn = (Button) findViewById(R.id.dialog_btn_No);

        titleView.setText(title);
        contentView.setText(content);

        if(yesBtnListener != null && noBtnListener != null){
            yesBtn.setOnClickListener(yesBtnListener);
            noBtn.setOnClickListener(noBtnListener);
        }
        else if(yesBtnListener != null && noBtnListener == null){
            yesBtn.setOnClickListener(yesBtnListener);
        }
        else{

        }
    }

    public InputDialog(Context context, String title, String content,
                       View.OnClickListener leftListener, View.OnClickListener rightListener){
        super(context, android.R.style.Theme_Translucent);
        this.title = title;
        this.content = content;
        this.yesBtnListener = leftListener;
        this.noBtnListener = rightListener;
    }
    public void ClearInput(){
        name.setText("");
        preference.setText("");
    }

    public String GetName(){
        return name.getText().toString();
    }
    public int GetPreference(){
        int output;
        try{
            output = Integer.parseInt(preference.getText().toString());
        }
        catch(NumberFormatException e){
            output = -1;
        }
        return output;
    }

}
