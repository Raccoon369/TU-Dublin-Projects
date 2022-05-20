// Proframmer: Sarunas Kucys
// Student ID: B00100612
// Date written: 15.04.2018
// Function: Software Development 2 / Assignment 2
// Note: Credit Union Program

//this class will allow the user to write data for each account and save to file
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SD2Ass2OpenAccount extends JFrame implements ActionListener
{
	//create GUI componements
	private JTextField idField, firstNameField, lastNameField, balanceField, overDraftField;
	private JButton enter, done; //send record to file, exit program
	private RandomAccessFile output, input; //file for input & output
	private SD2Ass2Record data;



	public SD2Ass2OpenAccount() //contructor -- initalises the Frame
	{
		super("Add new employee");

		//open file
		data = new SD2Ass2Record();

		//access record
		try
		{
			output = new RandomAccessFile( "accounts.dat", "rw" );
			System.out.println("In first try block");
		}

		catch ( IOException e)
		{
			System.err.println( e.toString() );
			System.exit(1);
		}

		setSize( 400, 200 );
		setLayout( new GridLayout(6,2) ); //set out layout

		//add components to GUI
		add( new JLabel("Account ID") );
		idField = new JTextField();
		add( idField );

		add( new JLabel("First Name") );
		firstNameField = new JTextField();
		add( firstNameField );

		add( new JLabel("Last Name") );
		lastNameField = new JTextField();
		add( lastNameField );

		add( new JLabel("Balance") );
		balanceField = new JTextField();
		add( balanceField );

		add( new JLabel("Overdraft Limit") );
		overDraftField = new JTextField();
		add( overDraftField );

		enter = new JButton ("Add Account");
		enter.addActionListener(this);
		add (enter);

		done = new JButton ("Done");
		done.addActionListener(this);
		add (done);

		setVisible( true );
	}


	public void addRecord() //create method for adding records to file
	{
		int accId = 0;
		Double balance, overDraftLimit;

		if ( ! idField.getText().equals( "" ) );
		{
			try
			{
				accId = Integer.parseInt( idField.getText() );
				//balance = Double.parseDouble( balanceField.getText() );
				//overDraftLimit = Double.parseDouble( overDraftField.getText() );

				System.out.println("In second try block");

				if (accId < 1 || accId > 100)  //validate account number is in range
				{
					JOptionPane.showMessageDialog(this, "Account ID must be between 1 & 100");
				}

				else if (accId > 0 && accId <= 100 )
				{
					//read file to check if id already exists.
					output.seek((long) (accId - 1) * SD2Ass2Record.size());
					data.read(output);

					if (data.getAccountID() == accId)  //if id exists,display dialog box to user
					{
						JOptionPane.showMessageDialog(this,"ID already exists! Please try a different Account ID");
						idField.setText(""); // clear account textfield
					}
					else //once conditions are met, data is written to file
					{
						data.setAccountID( accId );
						data.setFirstName( firstNameField.getText() );
						data.setLastName( lastNameField.getText() );
						balance = new Double( balanceField.getText() );
						data.setBalance( balance.doubleValue() );
						overDraftLimit = new Double( overDraftField.getText() );
						data.setOverDraftLimit( overDraftLimit.doubleValue() );

						output.seek( (long) ( accId-1 ) * SD2Ass2Record.size() );
						data.write( output );
						JOptionPane.showMessageDialog(this, "Employee Details Saved");
					}
				}

				//reset textfields
				idField.setText("");
				firstNameField.setText("");
				lastNameField.setText("");
				balanceField.setText("");
				overDraftField.setText("");

			} //end try statement

			catch (NumberFormatException nfe )
			{
				System.err.println("You must enter an integer account number");
			}
			catch (IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			}
		} //end initial if statement
	} //end addRecord method


	public void actionPerformed (ActionEvent e) //add actionperformed for exit button
	{
		addRecord();

		if (e.getSource() == done)
		{
			try
			{
				output.close();
			}

			catch (IOException io)
			{
				System.err.println( "File not closed properly\n" + io.toString() );
			}
			setVisible(false);
		}
	}

	//Instantiate a WriteRandonFile object and start the program
	public static void main(String [] args )
	{
		new SD2Ass2OpenAccount();
	}
} //closes class