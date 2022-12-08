package com.emented.weblab3.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@ApplicationScoped
@FacesValidator("yInputValidator")
public class YInputValidator implements Validator<Double> {

    private static String ERROR_MESSAGE = "The Y coordinate must be set by a number in the interval (-5..5)!";

    private static double LOWER_BOUND = -5;
    private static double UPPER_BOUND = 5;

    @Override
    public void validate(FacesContext context, UIComponent component, Double value) throws ValidatorException {

        if (value <= LOWER_BOUND || value >= UPPER_BOUND) {
            FacesMessage facesMessage = new FacesMessage(ERROR_MESSAGE);
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(facesMessage);
        }

    }
}
