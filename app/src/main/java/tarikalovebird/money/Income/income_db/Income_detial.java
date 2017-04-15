package tarikalovebird.money.Income.income_db;

/**
 * Created by TunasanG on 25/3/2560.
 */

public class Income_detial {
    public String id;
    public String name;
    public String amount;
    public String peroid;
    public String type;

    public Income_detial(String income) {
        String[] separated = income.split("/");
        this.id=separated[0];
        this.name=separated[1];
        this.amount = separated[2];
        this.peroid=separated[3];
        this.type = separated[4];
    }
}
