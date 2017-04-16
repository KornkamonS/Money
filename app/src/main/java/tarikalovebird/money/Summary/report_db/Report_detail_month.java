package tarikalovebird.money.Summary.report_db;

public class Report_detail_month {
    public String id;
    public String day;
    public String amount;
    public String month;
    public String year;

    public Report_detail_month(String income) {
        String[] separated = income.split("/");
        this.id = separated[0];
        this.day = separated[1];
        this.month = separated[2];
        this.year = separated[3];
        this.amount = separated[4];
    }
}
