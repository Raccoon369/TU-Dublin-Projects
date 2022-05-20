// Proframmer: Sarunas Kucys
// Student ID: B00100612
// Date written: 15.04.2018
// Function: Software Development 2 / Assignment 2
// Note: Credit Union Program

//Initial program for creation of file to contain various credit union accounts
import java.io.*;
import javax.swing.*;

public class SD2Ass2Record
{
	//declaring variables
	private int accountID;
	private String firstName, lastName;
	private double balance, overDraftLimit;

	public void read( RandomAccessFile file ) throws IOException
	{
		accountID = file.readInt();

		char first[] = new char[15];

		for ( int i=0; i < first.length; i++ )
			first[ i ] = file.readChar();

		firstName = new String (first);

		char last[] = new char[15];

		for (int i =0; i<last.length; i++)
			last[i] = file.readChar();

		lastName = new String (last);

		balance = file.readDouble();

		overDraftLimit = file.readDouble();
	}


	public void write(RandomAccessFile file) throws IOException
	{
		StringBuffer buf;

		file.writeInt(accountID);

		if (firstName != null)
			buf = new StringBuffer(firstName);

		else
		buf = new StringBuffer( 15 );

		buf.setLength( 15 );

		file.writeChars( buf.toString() );

		if ( lastName != null )
		buf = new StringBuffer( lastName );
		else
		buf = new StringBuffer( 15);

		buf.setLength( 15 );

		file.writeChars( buf.toString() );

		file.writeDouble( balance );

		file.writeDouble( overDraftLimit );
	}


	public void setAccountID( int a ) { accountID = a; }

	public int getAccountID() {return accountID;}

	public void setFirstName( String f) {firstName = f;}

	public String getFirstName() { return firstName; }

	public void setLastName ( String l) { lastName = l; }

	public String getLastName() { return lastName; }

	public void setBalance( double b) {balance = b;}

	public double getBalance() {return balance;}

	public void setOverDraftLimit( double o) {overDraftLimit = o;}

	public double getOverDraftLimit() {return overDraftLimit;}


	public static int size() {return 80;} //determines size (bytes) of each file
}


