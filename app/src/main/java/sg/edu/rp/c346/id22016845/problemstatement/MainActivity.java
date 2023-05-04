package sg.edu.rp.c346.id22016845.problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    EditText discount;
    ToggleButton svs;
    ToggleButton gst;
    Button split;
    Button reset;
    RadioButton cash;
    RadioButton payNow;
    RadioGroup radioGroupPayment;
    TextView totalAmount;
    TextView splitAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.editTextAmountInput);
        numPax = findViewById(R.id.editTextAmountInput2);
        discount = findViewById(R.id.discountAmount);
        svs = findViewById(R.id.toggleButtSVS);
        gst = findViewById(R.id.toggleButtGST);
        split = findViewById(R.id.Button3);
        reset = findViewById(R.id.Button4);
        cash = findViewById(R.id.radioButton);
        payNow = findViewById(R.id.radioButton2);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        totalAmount = findViewById(R.id.totalBillAmount);
        splitAmount = findViewById(R.id.eachPayAmount);

        split.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                double newAmount = 0.0;
                if(amount.getText().toString().trim().length()!=0 && numPax.getText().toString().trim().length()!=0){
                    if(!svs.isChecked() && !gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }else if (svs.isChecked() && !gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                    }else if (!svs.isChecked() && gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.1;
                    }else{
                        newAmount = Double.parseDouble(amount.getText().toString());
                    }
                }if (discount.getText().toString().trim().length() != 0){
                    newAmount *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                }
                totalAmount.setText("Total Bill: $" + String.format("%.2f", newAmount));

                int numPeople = Integer.parseInt(numPax.getText().toString());

                int radioChecked = radioGroupPayment.getCheckedRadioButtonId();
                if(radioChecked==R.id.radioButton && numPeople!=1 ){
                    splitAmount.setText("Each Pays $" + String.format("%.2f",newAmount/numPeople) + " in Cash");
                }else if (radioChecked==R.id.radioButton2 && numPeople!=1){
                    splitAmount.setText("Each Pays $" + String.format("%.2f",newAmount/numPeople) + " via PayNow");
                }else if (radioChecked==R.id.radioButton && numPeople==1){
                    splitAmount.setText("Paying $" + newAmount + " in Cash");
                }else if (radioChecked==R.id.radioButton2 && numPeople==1){
                    splitAmount.setText("Paying $" + newAmount + " via PayNow");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });


    }
}