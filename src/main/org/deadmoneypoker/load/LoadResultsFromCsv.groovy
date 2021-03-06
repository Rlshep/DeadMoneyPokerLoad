package org.deadmoneypoker.load

import com.opencsv.CSVReader
import groovy.io.FileType
import org.deadmoneypoker.dao.ResultDao
import org.deadmoneypoker.dao.SeasonDao
import org.deadmoneypoker.domain.Result

class LoadResultsFromCsv {
    static final DIR_PATH = '/Users/Richard/Documents/The Tournament Director 2/Data/saves'
    static final FILE_SUFFIX = '.CSV'
    static final HEADER_VALUE = "Rank"
    static final DATE_FORMAT = 'yyyyMMdd'

    ResultDao resultsDao
    SeasonDao seasonsDao

    static main(args) {
        if (3 > args.length) {
            println 'Invalid options. Usage : gradle -Pseason="Season 8" -Pchampionship=N -Penv=dev loadResults'
            return;
        }

        def loadResults = new LoadResultsFromCsv()
        loadResults.saveAllResults(args[0], args[1], args[2])
    }

    void saveAllResults(seasonName, championship, env) {
        resultsDao = new ResultDao(env)
        seasonsDao = new SeasonDao(env)
        def results = getAllResultsFiles()
        def season = seasonsDao.getSeason(seasonName)

        results.each { saveEachResult(season, championship, it) }
    }

    private def getAllResultsFiles() {
        def results = []
        def dir = new File(DIR_PATH)
        dir.eachFile(FileType.FILES) { file ->
            if (file.path.toUpperCase().endsWith(FILE_SUFFIX)) {
                results << file
            }
        }

        results
    }

    /**
     * CSV Key:
     * 0     1        2     3          4              5          6         7       8       9              10   11    12      13      14      15      16    17   18          19               20          21      22         23                24           25      26           27                 28            29         30         31           32       33        34     35   36           37              38             39   40
     * Rank,Nickname,Points,Buyin Cost,Prize Winnings,First Name,Last Name,Email 1,Email 2,Street Address,City,State,Zipcode,Country,Phone 1,Phone 2,Notes,Paid,Bounty Chip,Total Buyin Rake,Buyin Chips,Rebuys,Rebuys Cost,Total Rebuys Rake,Rebuys Chips,Add-ons,Add-ons Cost,Total Add-ons Rake,Add-ons Chips,Total Cost,Total Rake,Chips Bought,Time Out,Round Out,Hitman,Hits,Bounties Won,Bounty Winnings,Total Winnings,Take,Chip Count
     */
    private saveEachResult(season, championship, resultFile) {
        Date loadDate = getDateFromFile(resultFile)

        CSVReader reader = new CSVReader(new FileReader(resultFile));
        String [] fields;
        while ((fields = reader.readNext()) != null) {
//            println fields
            if (!fields[0].contains(HEADER_VALUE)) {
                def result = new Result(
                        seasonId: season.id,
                        datePlayed: loadDate,
                        playerName: fields[1],
                        rank: fields[0].toInteger(),
                        points: (new BigDecimal(fields[2])).intValue(),
                        winnings: (fields[38]) ? new BigDecimal(fields[38]) : BigDecimal.ZERO,
                        timeOut: (fields[32]) ? fields[32] : "",
                        roundOut: (fields[33]) ? fields[33] : 0,
                        hits: fields[35].toInteger(),
                        hitmanName: (fields[34]) ? fields[34] : "",
                        championshipInd: ("Y" == championship) ? true : false
                )

                resultsDao.insertResult(result)
            }
        }
    }

    Date getDateFromFile(file) {
        getDateFromFilePath(file.path)
    }

    Date getDateFromFilePath(path) {
        def final prefix = 'results_'

        def begin = path.indexOf(prefix) + prefix.length()
        def end = begin + DATE_FORMAT.length()
        def date = path.substring(begin, end)

        Date.parse(DATE_FORMAT, date)
    }

    int stringToInt(field) {
        def converted = field

        if (field instanceof String) {
            converted.replaceAll('"', '')
            converted = converted.toInteger()
        }

        converted
    }
}
