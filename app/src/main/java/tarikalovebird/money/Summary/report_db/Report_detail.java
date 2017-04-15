package tarikalovebird.money.Summary.report_db;

public class Report_detail {
    public String id;
    public String name;
    public String amount;
    public String day;
    public String month;
    public String year;
    public String inorout;
    public String type;

    public Report_detail(String income) {
        String[] separated = income.split("/");
        this.id=separated[0];
        this.name=separated[1];
        this.amount = separated[2];
        this.day=separated[3];
        this.month = separated[4];
        this.year = separated[5];
        this.inorout=separated[6];
        this.type = separated[7];
    }

}
