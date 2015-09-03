package org.deadmoneypoker.domain

class Result {
    static belongsTo = [season:Season]

    long id
    long seasonId
    Date datePlayed
    String playerName
    int rank
    int points
    BigDecimal winnings
    String timeOut
    String roundOut
    int hits
    String hitmanName
    boolean championshipInd

    static constraints = {
        playerName(blank: false)
        datePlayed(blank: false)
        timeOut(nullable: true)
        roundOut(nullable: true)
        hitmanName(nullable: true)
        championshipInd(default: false)
    }
}
