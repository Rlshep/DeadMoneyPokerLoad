package org.deadmoneypoker.dao

import org.deadmoneypoker.domain.Season

class SeasonDao extends DeadMoneyDao{
    SeasonDao(Object environment) {
        super(environment)
    }

    def getSeason(seasonName) {
        def query = "select id, name, start_year from season where name = '" + seasonName + "'"
        Season season = new Season()

        sql.eachRow query, {rowSeason->
            season.id = rowSeason.id
            season.name = rowSeason.name
            season.startYear = rowSeason.start_year
        }

        season
    }
}
