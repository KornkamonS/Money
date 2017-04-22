package tarikalovebird.money;

/**
 * Created by tunas on 22-Apr-17.
 */

public class helpcode {
    public String getMonthtext(int month)
    {
        String tvMonth="jan";
        switch (month)
        {
            case 1: tvMonth="Jan"; break;
            case 2:  tvMonth="Feb"; break;
            case 3:   tvMonth="Mar"; break;
            case 4:  tvMonth="April"; break;
            case 5:   tvMonth="May"; break;
            case 6:  tvMonth="Jun"; break;
            case 7:   tvMonth="July"; break;
            case 8:  tvMonth="Aug"; break;
            case 9:  tvMonth="Sep"; break;
            case 10:  tvMonth="Oct"; break;
            case 11:  tvMonth="Nov"; break;
            case 12:   tvMonth="Dec"; break;
        }
        return  tvMonth;
    }
    public int getIdpicType(int typeid)
    {
        switch (typeid)
        {
            case R.id.gift2: return  R.drawable.food1;
            case R.id.oil: return R.drawable.oil1;
            case R.id.home:  return R.drawable.home1;
            case R.id.elect: return R.drawable.elec1;
            case R.id.shop:  return R.drawable.shop1;
            case R.id.entertain: return R.drawable.entertain1;
            case R.id.pen:  return R.drawable.pen1;
            case R.id.study: return R.drawable.study1;
            case R.id.add2: return R.drawable.addd1;

            case R.id.gift: return R.drawable.gifts1;
            case R.id.salary: return R.drawable.salary1;
            case R.id.sale:  return R.drawable.sale1;
            case R.id.add: return R.drawable.add1;
            default: return R.drawable.add1;
        }
    }

}


