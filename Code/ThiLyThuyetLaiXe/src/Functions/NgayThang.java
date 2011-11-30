package Functions;

import java.util.Date;

public class NgayThang {
	//change from long to Date 
	public Date FromLongToDate(long time)
	{
		Date result = new Date();
		result.setTime(time);
		return result;
	}
}
