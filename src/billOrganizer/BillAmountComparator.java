package billOrganizer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class BillAmountComparator implements Comparator<Bill> {


	@Override
	public int compare(Bill bill, Bill bill2) {
		return bill.getAmountDue().compareTo(bill2.getAmountDue());

	}
	public static void main(String args []) throws ParseException{
		SimpleDateFormat formater=new SimpleDateFormat("MM/dd/yyyy");
		Bill bill=new Bill("Shifra", 34.30,formater.parse("12/24/2014"),BillType.EDUCATION);

		Bill bill2=new Bill("Etti",100.40,formater.parse("01/01/2014"),BillType.CLOTHING);
		BillDateComparator comparator=new BillDateComparator();
		System.out.println(comparator.compare(bill, bill2));
	}
}
