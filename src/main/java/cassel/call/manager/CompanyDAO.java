/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.call.manager;

import java.util.LinkedList;
import java.util.List;

/**
 * Data Access Object of Company information
 */
public class CompanyDAO {

    private static final List<CompanyBean> companies;

    static {
        //
        companies = new LinkedList<>();
        CompanyBean bean;
        //
        bean = new CompanyBean("e");
        bean.setCity("Sao Paulo");
        bean.setEmail("aparecida@email.com.br");
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

}
