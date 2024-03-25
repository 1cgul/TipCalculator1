package com.example.tipcalculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class HelloController {
    @FXML
    private TextField amount_tf;
    @FXML
    private TextField tip_tf;
    @FXML
    private Label percentageLabel;
    @FXML
    private TextField total_tf;
    @FXML
    private Button calculate_btn;
    @FXML
    private Slider tipSlider;

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15);
    public void initialize(){
        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                       tipPercentage = BigDecimal.valueOf(t1.intValue() / 100.0);
                       percentageLabel.setText(percent.format(tipPercentage));
                    }
                }
        );
    }
    @FXML
    private void calculateButtonPressed(ActionEvent event){
        try{
            BigDecimal amount = new BigDecimal(amount_tf.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tip_tf.setText((currency.format(tip)));
            total_tf.setText((currency.format(total)));
        }
        catch(NumberFormatException e){
            amount_tf.setText("Enter amount");
            amount_tf.selectAll();
            amount_tf.requestFocus();
        }
    }

}