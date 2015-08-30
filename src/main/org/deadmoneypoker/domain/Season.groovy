package org.deadmoneypoker.domain

class Season {
    static hasMany = [result: Result]

    long id
    String name
    int startYear

    static constraints = {
        name(blank: false, unique: true)
        startYear(blank: false, unique: true)
    }
}
