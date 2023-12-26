package com.ser;

import com.ser.blueline.IDocument;
import com.ser.evITAWeb.EvitaWebException;
import com.ser.evITAWeb.api.controls.IControl;

import com.ser.evITAWeb.api.controls.IControlContainer;
import com.ser.evITAWeb.api.controls.ISearchSelectionControl;
import com.ser.evITAWeb.scripting.keychange.KeyChangeScripting;
import org.slf4j.Logger;

public class EngDocument extends KeyChangeScripting {

    private static Logger log;

    public EngDocument(){
        super();
        this.log=super.log;
    }

    @Override
    public void onInit() throws EvitaWebException {
        lastVersionReadOnly();
        projectSelectorVisible();
    }

    private void lastVersionReadOnly() {

        IControl fldLastVersion = this.getDialog().getFieldByName("ccmReleased");

        if(getDocument().getDescriptorValue("ccmReleased")!=null && getDocument().getDescriptorValue("ccmReleased").equals("1")) {
            fldLastVersion.setReadonly(true);
        }
    }
    private void projectSelectorVisible() throws EvitaWebException {

        String prjCode = getDocument().getDescriptorValue("ccmPRJCard_code");
        ISearchSelectionControl projSelect = this.getDialog().getSearchSelectionControl("prjSelector");

        if(prjCode!= null && !prjCode.isEmpty() ) {
            if (projSelect != null) projSelect.setVisible(false);
        }
    }

}
