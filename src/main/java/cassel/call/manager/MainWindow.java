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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * Main window of call subject management
 */
public class MainWindow extends CFrame {

    /**
     * Builds the graphical interface
     *
     * @return this instance
     */
    public MainWindow buildGui() {
        CPanel mainPanel = new CPanel(new BorderLayout(10, 10));
        mainPanel.add(buildCompanyInfoPanel(), BorderLayout.NORTH);
        mainPanel.add(buildCallsPanel(), BorderLayout.CENTER);
        setContentPane(mainPanel);
        setDefaultCloseOperation(CFrame.EXIT_ON_CLOSE);
        setSize(630, 495);
        return this;
    }

    /**
     * Builds a panel with company information fields
     */
    private JComponent buildCompanyInfoPanel() {
        CPanel companyWrapperPanel = new CPanel(new BorderLayout());
        //
        CPanel companyTitlePanel = new CPanel(new FlowLayout());
        JLabel title = new JLabel("Ligações efetuadas");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, 24));
        companyTitlePanel.add(title);
        //
        CPanel formPanel = new CPanel(new FormLayoutManager());
        formPanel.add(new CLabeledField("         Empresa:"),       "A");
        formPanel.add(new CLabeledField("     Telefone 1:"),      "B");
        formPanel.add(new CLabeledField("            Cidade:"),        "A");
        formPanel.add(new CLabeledField("     Telefone 2:"),    "B");
        formPanel.add(new CLabeledField("Responsável 1:"),   "A");
        formPanel.add(new CLabeledField("         E-mail 1:"),        "B");
        formPanel.add(new CLabeledField("Responsável 2:"), "A");
        formPanel.add(new CLabeledField("         E-mail 2:"),      "B");
        //
        companyWrapperPanel.add(companyTitlePanel, BorderLayout.NORTH);
        companyWrapperPanel.add(formPanel, BorderLayout.CENTER);
        //
        return companyWrapperPanel;
    }
    
    private JComponent buildCallsPanel() {
        CPanel p = new CPanel(new BorderLayout(10, 10));
        //        
        // North part
        CPanel northPanel = new CPanel(new BorderLayout());
        northPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
        p.add(northPanel, BorderLayout.NORTH);
        //        
        // West part
        p.add(buildCallsTable(), BorderLayout.WEST);
        //        
        // East part
        p.add(buildCallFormPanel(), BorderLayout.CENTER);
        return p;
    }
    
    /**
     * Builds the call table
     * 
     * @return call table
     */
    private JComponent buildCallsTable() {
        CPanel panel = new CPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        String[][] data = {{"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}, {"", "", ""}};
        String[] cols = {"Assunto", "Data", "Observação"};
        JTable table = new JTable(data, cols);
        table.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.setSize(new Dimension(300, 900));
        sp.setPreferredSize(new Dimension(300, 900));
        panel.add(sp);
        panel.setSize(new Dimension(300, 900));
        panel.setPreferredSize(new Dimension(300, 900));
        return panel;
    }
    
    /**
     * Builds the call form panel
     * 
     * @return JComponent
     */
    private JComponent buildCallFormPanel() {
        CPanel wrapper = new CPanel(new BorderLayout());
        // Form itself
        CPanel formPanel = new CPanel(new FormLayoutManager(1));
        formPanel.add(new CLabeledField("      Assunto:"),       "A");
        formPanel.add(new CLabeledField("            Data:"),       "A");
        formPanel.add(new CLabeledArea("Observação:"),       "A");
        // Footer
        CPanel footer = new CPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.add(new CButton("Gravar"));
        // Adds to the wrapper panel
        wrapper.add(formPanel, BorderLayout.CENTER);
        wrapper.add(footer, BorderLayout.SOUTH);
        //
        return wrapper;
    }

}
