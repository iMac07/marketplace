package org.rmj.marketplace.validator;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.ValidationFacade;
import com.jfoenix.validation.base.ValidatorBase;

import javafx.scene.control.TextField;

public class ValidatorUtils {
	
	
	public static NumberValidator addNumberOnlyValidator(TextField textField) {
		NumberValidator numberValidator = new NumberValidator(textField);
		numberValidator.validate();
		
		return numberValidator;
	}
	
	public static DecimalValidator addDecimalValidator(TextField textField) {
		DecimalValidator decimalValidator = new DecimalValidator(textField);
		decimalValidator.validate();
		
		return decimalValidator;
	}
	
	public static MoneyValidator addMoneyValidator(TextField textField) {
		MoneyValidator moneyValidator = new MoneyValidator(textField);
		moneyValidator.validate();
		
		return moneyValidator;
	}
	
	public static MaxLengthValidator addMaxLengthValidator(TextField textField, int maxLength) {
		MaxLengthValidator maxLengthValidator = new MaxLengthValidator(textField);
		maxLengthValidator.validate(maxLength);
		
		return maxLengthValidator;
	}
	
}
