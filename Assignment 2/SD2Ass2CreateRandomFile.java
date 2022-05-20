// Proframmer: Sarunas Kucys
// Student ID: B00100612
// Date written: 15.04.2018
// Function: Software Development 2 / Assignment 2
// Note: Credit Union Program

// This section will create a random access file and
// will write 100 empty records to file.

import java.io.*;

public class SD2Ass2CreateRandomFile
{
	private SD2Ass2Record blank;
	private RandomAccessFile file;

	public SD2Ass2CreateRandomFile()
	{
		blank = new SD2Ass2Record();

		try
		{
			file = new RandomAccessFile( "accounts.dat", "rw" );
			for (int i=0; i<100; i++)

			blank.write( file );
		}

		catch(IOException e)

		{
			System.err.println( "File not opened properly\n" + e.toString() );
			System.exit( 1 );
		}
	}

	public static void main( String [] args )
	{
		SD2Ass2CreateRandomFile employeeDetails = new SD2Ass2CreateRandomFile();
	}
}