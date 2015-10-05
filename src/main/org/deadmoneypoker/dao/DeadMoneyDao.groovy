package org.deadmoneypoker.dao

import groovy.sql.Sql

class DeadMoneyDao {
    private static final PROD = "prod"
    private def dbUrl = "jdbc:postgresql://localhost/richard_dev"
    private def dbUser = "postgres"
    private def dbPassword = "password"
    private def dbUrl_prod = "jdbc:postgresql://pellefant.db.elephantsql.com:5432/vtpjadxs"
    private def dbUser_prod = "vtpjadxs"
    private def dbPassword_prod = "password"
    private def dbDriver = "org.postgresql.Driver"
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
