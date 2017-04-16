package tarikalovebird.money.Summary.report_db;

public class Report_detail_year {
    public String id;
    public String amount;
    public String month;
    public String year;

    public Report_detail_year(String income) {
        String[] separated = income.split("/");
        this.id = separated[0];
        this.month = separated[1];
        this.year = separated[2];
        this.amount = separated[3];
    }
}
