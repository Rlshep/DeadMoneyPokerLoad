package org.deadmoneypoker.dao

import groovy.sql.Sql

class DeadMoneyDao {
    private static final PROD = "prod"


    protected def sql

    public DeadMoneyDao(env) {
        if (PROD == env) {
            sql = Sql.newInstance(dbUrl_prod, dbUser_prod, dbPassword_prod, dbDriver)
        } else {
            sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriver)
        }
    }

    public def closeDb() {
        sql.close()
    }
}
