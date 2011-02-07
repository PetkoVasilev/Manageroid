package com.manageroid.application.proxy;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AllTasks
{
	public static ArrayList<ManageroidTask> allMyTasks;
	
	private final static String FILE_NAME = "manageroid_tasks.bin";
	
	public static void archive(Context context)
	{
		// not tested
		try
		{
			FileOutputStream fos = context.openFileOutput(FILE_NAME, 0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(allMyTasks);
			fos.close();
		}
		catch (IOException fnfe) {}
	}

	public static void load(Context context)
	{
		try
		{
			allMyTasks = null;
			allMyTasks = new ArrayList<ManageroidTask>();
			FileInputStream fos = context.openFileInput(FILE_NAME);
			byte[] readBytes = new byte[1024*1024];  // 1MB should be enough for everybody...
			fos.read(readBytes);
			fos.close();

			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(readBytes));
			try
			{
				allMyTasks = (ArrayList<ManageroidTask>) ois.readObject();
				ois.close();
			}
			catch (ClassNotFoundException cnfe) {}
		}
		catch (IOException ioe) {}
	}
}









