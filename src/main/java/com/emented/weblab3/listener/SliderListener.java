package com.emented.weblab3.listener;

import com.emented.weblab3.model.Table;
import lombok.Data;
import org.primefaces.event.SlideEndEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ValueChangeEvent;

@Data
@ManagedBean
@ApplicationScoped
public class SliderListener {

    @ManagedProperty("#{table}")
    private Table table;


    public void onSlideEnd(SlideEndEvent event) {
        table.changeR(event.getValue());
    }

}
