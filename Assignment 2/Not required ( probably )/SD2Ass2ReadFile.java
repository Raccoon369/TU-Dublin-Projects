// Proframmer: Sarunas Kucys
// Student ID: B00100612
// Date written: 15.04.2018
// Function: Software Development 2 / Assignment 2
// Note: Credit Union Program

//this program read a random access file sequentally and
//displays the content one record at a time in text fields

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class SD2Ass2ReadFile extends JFrame implements ActionListener
{
	//create GUI components
	private JTextField idField, firstNameField, lastNameField, balanceField, overDraftField;
	private JButton next, done;
	private RandomAccessFile input, output;
	private SD2Ass2Record data;

	public SD2Ass2ReadFile() //constructor
	{
		super ("View Employees");

		//Open the file
		try
		{
			//set up files for read & write
			input = new RandomAccessFile( "accounts.dat", "rw" );
			output = new RandomAccessFile( "accounts.dat", "rw" );
		}
		catch ( IOException e )
		{
			System.err.println(e.toString() );
			System.exit(1);
		}

		data = new SD2Ass2Record();

		//set out layout
		setSize( 300, 150 );
		setLayout( new GridLayout(6,2) );

		add( new JLabel( "Account ID" ) );
		idField = new JTextField(15);
		idField.setEditable( false );
		add( idField );

		add( new JLabel( "First Name" ) );
		firstNameField = new JTextField(15);
		firstNameField.setEditable( false );
		add( firstNameField );

		add( new JLabel( "Last Name" ) );
		lastNameField = new JTextField(15);
		lastNameField.setEditable( false );
		add( lastNameField );

		add( new JLabel( "Balance" ) );
		balanceField = new JTextField(15);
		balanceField.setEditable( false );
		add( balanceField );

		add( new JLabel( "Overdraft Limit" ) );
		overDraftField = new JTextField(15);
		overDraftField.setEditable( false );
		add( overDraftField );

		next = new JButton ("Next");
		next.addActionListener(this);
		add (next);

		done = new JButton ("Done");
		done.addActionListener(this);
		add (done);

		setVisible( true );
	}

	//set up handler for various actionlisteners
	public void actionPerformed( ActionEvent e )
	{
		//read account details when account number entered
		if (e.getSource() == next)
		{
			readRecord();
		}
		else
		closeFile();
	}//ends actionperformed method

	//READ RECORD METHOD
	public void readRecord()
	{
		DecimalFormat twoDigits = new DecimalFormat( "0.00" );

		try
		{
			do{
				data.read(input);
			}

			while(data.getAccountID() == 0);

			idField.setText(String.valueOf( data.getAccountID() ) );
			firstNameField.setText( data.getFirstName() );
			lastNameField.setText( data.getLastName() );
			balanceField.setText( String.valueOf( twoDigits.format( data.getBalance() ) ) );
			overDraftField.setText( String.valueOf( twoDigits.format( data.getOverDraftLimit() ) ) );
		}

		catch (EOFException eof )
		{
			closeFile();
		}

		catch (IOException e )
		{
			System.err.println("Error during read from file\n " + e.toString() );
			System.exit( 1 );
		}
	}

	//method to closefile for exceptions
	private void closeFile()
	{
		try
		{
			input.close();
			System.exit( 0 );
		}
		catch( IOException e)
		{
			System.err.println( "Error closing file \n" + e.toString() );
		}
	}
	//Instantiate a ReadRandomFile object and start the program

	public static void main(String [] args)
	{
		new SD2Ass2ReadFile();
	}
}//end class
