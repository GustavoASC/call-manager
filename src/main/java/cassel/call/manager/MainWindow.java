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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 * Main window of call subject management
 */
public class MainWindow extends CFrame {

    /* Current company */
    private CompanyBean currentCompany;
    /* Company name */
    private CLabeledField company;
    /* City name */
    private CLabeledField city;
    /* Responsible 1 */
    private CLabeledField responsible;
    /* Phone number */
    private CLabeledField phoneNumber;
    /* E-mail */
    private CLabeledField email;
    /* Responsible 2 */
    private CLabeledField responsible2;
    /* Phone number 2 */
    private CLabeledField phoneNumber2;
    /* E-mail 2 */
    private CLabeledField email2;
    /* History table */
    private JTable table;
    /* List of all company calls */
    private List<CallBean> calls;
    /* Call subject */
    private CLabeledField callSubject;
    /* Call date */
    private CLabeledField callDate;
    /* Call general info */
    private CLabeledArea callGeneralInfo;

    /**
     * Creates the main window with the loaded companies from database
     */
    public MainWindow() {
        this.buildGui();
    }

    /**
     * Builds the graphical interface
     *
     * @return this instance
     */
    private MainWindow buildGui() {
        CPanel mainPanel = new CPanel(new BorderLayout(10, 10));
        mainPanel.add(buildCompanyInfoPanel(), BorderLayout.NORTH);
        mainPanel.add(buildCallsPanel(), BorderLayout.CENTER);
        setContentPane(mainPanel);
        setDefaultCloseOperation(CFrame.EXIT_ON_CLOSE);
        setSize(633, 520);
        return this;
    }

    /**
     * Builds a panel with company information fields
     */
    private JComponent buildCompanyInfoPanel() {
        CPanel companyWrapperPanel = new CPanel(new BorderLayout());
        //
        CPanel companyTitlePanel = new CPanel(new FlowLayout());
        JLabel title = new JLabel("Empresa");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, 24));
        companyTitlePanel.add(title);
        //
        CPanel formPanel = new CPanel(new FormLayoutManager());
        company = new CLabeledField("         Empresa:");
        company.getField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {

            }

            @Override
            public void focusLost(FocusEvent event) {
                String text = company.getField().getText();
                currentCompany = new CompanyDAO().loadCompanyFromName(text);
                if (currentCompany != null) {
                    loadInterfaceFromCompanyInfo(currentCompany);
                }
            }
        });
        phoneNumber = new CLabeledField("     Telefone 1:");
        city = new CLabeledField("            Cidade:");
        phoneNumber = new CLabeledField("     Telefone 2:");
        phoneNumber2 = new CLabeledField("     Telefone 2:");
        responsible = new CLabeledField("Responsável 1:");
        email = new CLabeledField("         E-mail 1:");
        responsible2 = new CLabeledField("Responsável 2:");
        email2 = new CLabeledField("         E-mail 2:");
        formPanel.add(company, "A");
        formPanel.add(phoneNumber, "B");
        formPanel.add(city, "A");
        formPanel.add(phoneNumber2, "B");
        formPanel.add(responsible, "A");
        formPanel.add(email, "B");
        formPanel.add(responsible2, "A");
        formPanel.add(email2, "B");
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
        // Calls title
        CPanel callTitlePanel = new CPanel(new BorderLayout());
        CPanel titlePanel = new CPanel();
        JLabel title = new JLabel("Ligações");
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.BOLD, 18));
        titlePanel.add(title);
        callTitlePanel.add(titlePanel, BorderLayout.CENTER);
        callTitlePanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
        northPanel.add(callTitlePanel, BorderLayout.CENTER);
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
        CPanel wrapperPanel = new CPanel(new BorderLayout(5, 5));
        //
        CPanel panel = new CPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        String[][] data = {};
        String[] cols = {"Assunto", "Data", "Observação"};
        table = new JTable();
        table.setModel(new DefaultTableModel(data, cols));
        table.setBorder(BorderFactory.createEmptyBorder());
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                //
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int firstRow = event.getLastIndex();
                //
                String subject = (String) model.getValueAt(firstRow, 0);
                String date = (String) model.getValueAt(firstRow, 1);
                String generalInfo = (String) model.getValueAt(firstRow, 2);
                //
                loadInterfaceCallFromBean(new CallBean(subject)
                        .setDate(date)
                        .setGeneralInfo(generalInfo));

            }
        });

        JScrollPane sp = new JScrollPane(table);

        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.setSize(new Dimension(300, 900));
        sp.setPreferredSize(new Dimension(300, 900));
        panel.add(sp);

        panel.setSize(new Dimension(300, 900));
        panel.setPreferredSize(new Dimension(300, 900));
        //
        CPanel titlePanel = new CPanel();

        titlePanel.add(new JLabel("Histórico de ligações"));
        wrapperPanel.add(titlePanel, BorderLayout.NORTH);
        //

        wrapperPanel.add(panel, BorderLayout.EAST);
        return wrapperPanel;
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
        callSubject = new CLabeledField("      Assunto:");
        callDate = new CLabeledField("            Data:");
        callGeneralInfo = new CLabeledArea("Observação:");
        formPanel.add(callSubject, "A");
        formPanel.add(callDate, "A");
        formPanel.add(callGeneralInfo, "A");
        // Footer
        CPanel footer = new CPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.add(new CButton("Cancelar"));
        //
        CButton saveButton = new CButton("Gravar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int result = JOptionPane.showConfirmDialog(null, "Confirma a gravação dos dados?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    loadCompanyBeanFromInterface(currentCompany);
                    new CompanyDAO().insertOrUpdate(currentCompany);
                    JOptionPane.showMessageDialog(null, "Dados gravados com sucesso.");
                }
            }
        });
        //
        footer.add(saveButton);
        // Adds to the wrapper panel
        wrapper.add(formPanel, BorderLayout.CENTER);
        wrapper.add(footer, BorderLayout.SOUTH);
        //
        return wrapper;
    }

    /**
     * Loads interface from the specified bean
     *
     * @param bean company bean
     */
    private void loadInterfaceFromCompanyInfo(CompanyBean bean) {
        // Load general fields
        city.getField().setText(bean.getCity());
        responsible.getField().setText(bean.getResponsible());
        phoneNumber.getField().setText(bean.getPhoneNumber());
        email.getField().setText(bean.getEmail());
        responsible2.getField().setText(bean.getResponsible2());
        phoneNumber2.getField().setText(bean.getPhoneNumber2());
        email2.getField().setText(bean.getEmail2());
        // Load history table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[3];
        List<CallBean> currentCalls = currentCompany.getCalls();
        for (int i = 0; i < currentCalls.size(); i++) {
            rowData[0] = currentCalls.get(i).getSubject();
            rowData[1] = currentCalls.get(i).getDate();
            rowData[2] = currentCalls.get(i).getGeneralInfo();
            model.addRow(rowData);
        }
    }

    /**
     * Loads interface from the specified bean
     *
     * @param bean company bean
     */
    private void loadCompanyBeanFromInterface(CompanyBean bean) {
        //
        String cityText = city.getField().getText();
        bean.setCity(cityText);
        bean.setResponsible(responsible.getField().getText());
        bean.setPhoneNumber(phoneNumber.getField().getText());
        bean.setEmail(email.getField().getText());
        bean.setResponsible2(responsible2.getField().getText());
        bean.setPhoneNumber2(phoneNumber2.getField().getText());
        bean.setEmail2(email2.getField().getText());
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

}
