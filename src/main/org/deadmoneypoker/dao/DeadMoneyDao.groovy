package org.deadmoneypoker.dao

import groovy.sql.Sql

class DeadMoneyDao {
    private def dbUrl = "jdbc:postgresql://localhost/richard_dev"
    private def dbUser = "postgres"
    private def dbPassword = "postgres"
    private def dbDriver = "org.postgresql.Driver"

    protected def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriver)

    public def closeDb() {
        sql.close()
    }
}
