/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.call.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object of Company information
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
     */
    public CompanyBean loadCompanyFromName(String name) {
        ensureConnected();
        for (int i = 0; i < companies.size(); i++) {
            CompanyBean bean = companies.get(i);
            if (bean.getCompany().trim().equals(name.trim())) {
                return bean;
            }
        }
        return null;
    }

    /**
     * Insert the specified company
     *
     * @param target target bean
     */
    public void insertOrUpdate(CompanyBean target) {
        ensureConnected();
        String targetCompany = target.getCompany();
        for (int i = 0; i < companies.size(); i++) {
            CompanyBean bean = companies.get(i);
            if (bean.getCompany().trim().equals(targetCompany.trim())) {
                companies.set(i, target);
                return;
            }
        }
        companies.add(target);
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
                // create a connection to the database
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
