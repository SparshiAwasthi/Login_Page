package cs;

import java.util.ArrayList;
import java.util.List;

public class ValidateData 
{
	public List<String> validateLogin(String username,String pswd)
	{
		ArrayList<String> list = new ArrayList<String>();
		
		if(username.isEmpty())
		{
			list.add("User name can not be empty");
		}
		else if(username.length() < 4)
		{
			list.add("User name is too short");
		}
		else if(username.length() > 25)
		{
			list.add("User Name is too long");
		}
		if(pswd.isEmpty())
		{
			list.add("Password can not be empty");
		}
		else if(pswd.length() < 4)
		{
			list.add("Password is too short");
		}
		else if(pswd.length() > 25)
		{
			list.add("Password is too long");
		}
		return list;
	}
}

