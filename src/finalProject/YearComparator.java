package finalProject;

import java.util.Comparator;

public class YearComparator  implements  Comparator<CompletedCourse> {

	@Override
	public int compare(CompletedCourse c, CompletedCourse c2) {
		return Integer.valueOf(c.getYearCompleted()).compareTo(Integer.valueOf(c2.getYearCompleted()));
	}

}
