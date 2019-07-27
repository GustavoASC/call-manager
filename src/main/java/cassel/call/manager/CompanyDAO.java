/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.call.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Data Access Object of Company information.
 * 
 * <p> This DAO requires the table to be already created.
 * This can be done with the following DDL:
 * 
 * .open /home/gustavo/Documentos/sources/call-manager/database.db
 * 'create table companies (name text primary key, city text, responsible text, phoneNumber text, email text, responsible2 text, phoneNumber2 test, email2 text);'
 * 'create table calls (companyName text, subject text, date text, generalInfo text);'
 */
public class CompanyDAO {

    private static final List<CompanyBean> companies;
    /* Database connection */
    private static Connection conn;

    static {
        //
        companies = new LinkedList<>();
        CompanyBean bean;
        //
        bean = new CompanyBean("e");
        bean.setCity("Sao Paulo");
        bean.setEmail("aparecida@email.com.br");
        bean.setEmail2("guscassel@gmail.com");
        bean.setResponsible("Marco Cassel");
        bean.setResponsible2("Gustavo Cassel");
        bean.setPhoneNumber("(051) 9 9972-7792");
        bean.setPhoneNumber2("(051) 9 9944-7268");
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Primeira ligação")
                .setDate("01/10/2017")
                .setGeneralInfo("Essa é minha observação geral"));;
        bean.addCall(new CallBean("Segunda ligação")
                .setDate("05/07/2018")
                .setGeneralInfo("Essa é minha observação geral"));;
        companies.add(bean);
        //
        bean = new CompanyBean("Ramarin");
        bean.setCity("Novo Hamburgo");
        bean.setEmail("nh@ramarin.com.br");
        companies.add(bean);
    }

    /**
     * Loads company information from it's name
     *
     * @param name company name
     * @return company information
     * @throws SQLException if the command fails for any reason
     */
    public CompanyBean loadCompanyFromName(String name) {
        try {
            ensureConnected();
            //
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from companies where name = '" + name + "'");
            //
            if (rs.next()) {
                //
                CompanyBean bean = new CompanyBean(name);
                bean.setCity(rs.getString("city"));
                bean.setResponsible(rs.getString("responsible"));
                bean.setPhoneNumber(rs.getString("phoneNumber"));
                bean.setEmail(rs.getString("email"));
                bean.setResponsible2(rs.getString("responsible2"));
                bean.setPhoneNumber2(rs.getString("phoneNumber2"));
                bean.setEmail2(rs.getString("email2"));
                //
                List<CallBean> callBeans = loadCompanyCalls(name);
                bean.setCalls(callBeans);
                return bean;
                //
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Load company calls
     * 
     * @param companyName company name
     * @return company calls
     * @throws SQLException if any error occurs
     */
    private List<CallBean> loadCompanyCalls(String companyName) throws SQLException {
        //
        List<CallBean> callBeans = new LinkedList<>();
        //
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from calls where companyName = '" + companyName + "'");
        //
        while (rs.next()) {
            //
            CallBean call = new CallBean(rs.getString("subject"));
            call.setDate(rs.getString("date"));
            call.setGeneralInfo(rs.getString("generalInfo"));
            //
            callBeans.add(call);
        }
        return callBeans;
    }

    /**
     * Inserts or updates the specified company information
     *
     * @param target target bean
     */
    public void insertOrUpdate(CompanyBean target) {
        try {
            ensureConnected();
            Statement stmt = conn.createStatement();
            //
            StringBuilder values = new StringBuilder();
            values.append("\"").append(target.getCompany()).append("\", ");
            values.append("\"").append(target.getCity()).append("\", ");
            values.append("\"").append(target.getResponsible()).append("\", ");
            values.append("\"").append(target.getPhoneNumber()).append("\", ");
            values.append("\"").append(target.getEmail()).append("\", ");
            values.append("\"").append(target.getResponsible2()).append("\", ");
            values.append("\"").append(target.getPhoneNumber2()).append("\", ");
            values.append("\"").append(target.getEmail2()).append("\"");
            //
            String commandString = "insert or replace into companies (name, city, responsible, phoneNumber, email, responsible2, phoneNumber2, email2) values (" + values.toString() + ");";
            stmt.executeUpdate(commandString);
            //
            // Insert calls
            insertOrUpdateCalls(target.getCompany(), target.getCalls());
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Insert or udpate calls into database
     * 
     * @param companyName company name
     * @param calls company calls
     */
    private void insertOrUpdateCalls(String companyName, List<CallBean> calls) {
        ensureConnected();
        try {
            removeOldCalls(companyName);
            try {
                for (CallBean call : calls) {
                    Statement stmt = conn.createStatement();
                    //
                    StringBuilder values = new StringBuilder();
                    values.append("\"").append(companyName).append("\", ");
                    values.append("\"").append(call.getSubject()).append("\", ");
                    values.append("\"").append(call.getDate()).append("\", ");
                    values.append("\"").append(call.getGeneralInfo()).append("\"");
                    //
                    //
                    String commandString = "insert or replace into calls (companyName, subject, date, generalInfo) values (" + values.toString() + ");";
                    stmt.executeUpdate(commandString);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Remove old calls from database
     * 
     * @param companyName company name
     */
    private void removeOldCalls(String companyName) throws SQLException {
        try {
            String commandString = "delete from calls where companyName = \"" + companyName + "\"";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(commandString);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover dados antigos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            throw ex;
        }
        
    }

    /**
     * Fires the SQL command on database
     */
    private void fireSqlCommand(String sql) {
        new SQLiteManager().executeSql(sql);
    }

    /**
     * Connect to the database
     */
    private void ensureConnected() {
        if (conn == null) {
            try {
                // Creates a connection to the database
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:database.db";
                conn = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
