package com.emented.weblab3.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class SessionIdGetterImpl implements SessionIdGetter{


    @Override
    public String getCurrentSessionId() {

        FacesContext facesContext =  FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getSessionId(false);

    }
}
