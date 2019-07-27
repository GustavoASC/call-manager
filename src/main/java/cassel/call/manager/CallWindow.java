/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.call.manager;

import cassel.util.gui.CButton;
import cassel.util.gui.CFrame;
import cassel.util.gui.CLabeledArea;
import cassel.util.gui.CLabeledField;
import cassel.util.gui.CPanel;
import cassel.util.gui.FormLayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;

/**
 * Frame with call information
 *
 * @author gustavo
 */
public class CallWindow extends CFrame {

    /* Bean with call information */
    private final CallBean callBean;
    /* Call subject */
    private CLabeledField callSubject;
    /* Call date */
    private CLabeledField callDate;
    /* Call general info */
    private CLabeledArea callGeneralInfo;
    /* Cancel button */
    private CButton cancelButton;
    /* OK button */
    private CButton okButton;
    /* Boolean indicating if user confirmed */
    boolean userConfirmed;

    /**
     * Creates a new call frame
     *
     * @param callBean bean with call information
     */
    public CallWindow(CallBean callBean) {
        this.callBean = callBean;
        buildGui();
        loadInterfaceCallFromBean(callBean);
    }

    public CallBean getCallBean() {
        return callBean;
    }

    public boolean isUserConfirmed() {
        return userConfirmed;
    }

    /**
     * Builds the call window
     */
    private void buildGui() {
        setTitle("Informações da ligação");
        setContentPane(buildMainPanel());
        setSize(620, 230);
    }

    /**
     * Builds the form panel
     *
     * @return JComponent
     */
    private Container buildMainPanel() {
        CPanel wrapper = new CPanel(new BorderLayout());
        wrapper.add(buildFormPanel(), BorderLayout.CENTER);
        wrapper.add(buildFooter(), BorderLayout.SOUTH);
        return wrapper;
    }

    /**
     * Builds the form panel
     *
     * @return JComponent
     */
    private JComponent buildFormPanel() {
        CPanel formPanel = new CPanel(new FormLayoutManager(2));
        callSubject = new CLabeledField("      Assunto:");
        callDate = new CLabeledField("            Data:");
        callGeneralInfo = new CLabeledArea("Observação:");
        formPanel.add(callSubject, "A");
        formPanel.add(callDate, "A");
        formPanel.add(callGeneralInfo, "A");
        return formPanel;
    }

    /**
     * Builds the footer panel
     *
     * @return JComponent
     */
    private Component buildFooter() {
        CPanel footer = new CPanel(new FlowLayout(FlowLayout.RIGHT));
        cancelButton = new CButton("Cancelar");
        cancelButton.addActionListener((ActionEvent e) -> {
            userConfirmed = false;
            loadBeanFromInterface(callBean);
            dispose();
        });
        okButton = new CButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            userConfirmed = true;
            loadBeanFromInterface(callBean);
            dispose();
        });
        footer.add(cancelButton);
        footer.add(okButton);
        return footer;
    }

    /**
     * Load call fields from bean
     *
     * @param bean
     */
    private void loadInterfaceCallFromBean(CallBean bean) {
        callSubject.getField().setText(bean.getSubject());
        callDate.getField().setText(bean.getDate());
        callGeneralInfo.getArea().setText(bean.getGeneralInfo());
    }

    /**
     * Load call fields from bean
     *
     * @param bean
     */
    private void loadBeanFromInterface(CallBean bean) {
        bean.setSubject(callSubject.getField().getText());
        bean.setDate(callDate.getField().getText());
        bean.setGeneralInfo(callGeneralInfo.getArea().getText());
    }

}
