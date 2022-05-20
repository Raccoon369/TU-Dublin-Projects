// Proframmer: Sarunas Kucys
// Student ID: B00100612
// Date written: 15.04.2018
// Function: Software Development 2 / Assignment 2
// Note: Credit Union Program

//this class will allow the user to read data for a particular account
//and delete the account
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class SD2Ass2CloseAccount extends JFrame implements ActionListener
{
	//create GUI components
	private JTextField idField, firstNameField, lastNameField, balanceField, overDraftField;;
	private JButton closeBut, exitBut;
	private RandomAccessFile input, output;
	private SD2Ass2Record data;

	public SD2Ass2CloseAccount()
	{
		super ("Close Account");

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

		//create the components of the Frame
		setSize( 400, 200 );
		setLayout( new GridLayout(6, 2) );


		add( new JLabel( "ID Number (1 - 100)" ) );
		idField = new JTextField(15);
		idField.setEditable( true );
		add( idField );
		idField.addActionListener(this);

		add( new JLabel( "First Name" ) );
		firstNameField = new JTextField(15);
		firstNameField.setEditable( false );
		add( firstNameField );

		add( new JLabel( "Last Name" ) );
		lastNameField = new JTextField(15);
		lastNameField.setEditable( false );
		add( lastNameField );

		add( new JLabel( "Account Balance" ) );
		balanceField = new JTextField(15);
		balanceField.setEditable( false );
		add( balanceField );

		add( new JLabel( "Overdraft Limit" ) );
		overDraftField = new JTextField(15);
		overDraftField.setEditable( false );
		add( overDraftField );


		closeBut = new JButton( "Close Account");
		closeBut.addActionListener( this );
		add(closeBut);
		closeBut.setEnabled(true);

		exitBut = new JButton( "Exit" );
		exitBut.addActionListener( this );
		add(exitBut);

		setVisible( true );
	}


	public void actionPerformed( ActionEvent e )
	{
		//read account details when account number entered
		if (! idField.getText().equals(""))
		{
			readRecord(); //calling readRecord method
			closeBut.setEnabled(true);
		}

		if (e.getSource() == exitBut)   //exit maintenance menu
		{
			setVisible(false);
		}

		if (e.getSource() == closeBut)
		{
			try
			{
				int accId = Integer.parseInt( idField.getText() );

				int dialogButton = JOptionPane.showConfirmDialog (null, "           Are you sure?","ACCOUNT DELETION WARNING",JOptionPane.YES_NO_OPTION);

				if (dialogButton == JOptionPane.YES_OPTION)
				{
					if ( data.getBalance() > 0 )
					{
						JOptionPane.showMessageDialog(this, "Closing forbidden. Account is not empty");
					}

					if ( data.getBalance() < 0 )
					{
						JOptionPane.showMessageDialog(this, "Closing forbidden. Account is overdrawn.");
					}

					if ( data.getBalance() == 0 )
					{
						data.setAccountID( 0 );
						data.setFirstName( null );
						data.setLastName( null);
						data.setBalance( 0);
						data.setOverDraftLimit( 0);

						output.seek( (long) ( accId-1 ) * SD2Ass2Record.size() );
						data.write( output );

						JOptionPane.showMessageDialog(this, "Account has been deleted");
						idField.setText("");
						firstNameField.setText("");
						lastNameField.setText("");
						balanceField.setText("");
						overDraftField.setText("");
					}
				}

				if (dialogButton == JOptionPane.NO_OPTION)
				{
					idField.setText("");
					firstNameField.setText("");
					lastNameField.setText("");
					balanceField.setText("");
					overDraftField.setText("");
        		}

			}//end try

			catch (NumberFormatException nfe )
			{
				System.err.println("You must enter an integer account number");
			}
			catch (IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			}
		} //end if
	} //end actionPerformed


	public void readRecord()
	{
		DecimalFormat twoDigits = new DecimalFormat( "0.00" );
		try
		{
			int accountNumber = Integer.parseInt(idField.getText());

			if (accountNumber < 1 || accountNumber > 100)
			{
				JOptionPane.showMessageDialog(this, "Account does not exist");
			}
			else
			{
				input.seek( (accountNumber - 1)*SD2Ass2Record.size());
				data.read(input);

				idField.setText(String.valueOf( data.getAccountID() ) );
				firstNameField.setText( data.getFirstName() );
				lastNameField.setText( data.getLastName() );
				balanceField.setText( String.valueOf( twoDigits.format( data.getBalance() ) ) );
				overDraftField.setText( String.valueOf( twoDigits.format( data.getOverDraftLimit() ) ) );
			}
			if (data.getAccountID() == 0)
			{
				JOptionPane.showMessageDialog(this, "Account does not exist");
				idField.setText("");
			}
		}//end try statement
		catch (EOFException eof )
		{
			closeFile();
		}
		catch (IOException e )
		{
			System.err.println("Error during read from file\n " + e.toString() );
			System.exit( 1 );
		}
	} // end readRecord method


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
	} // end closeFile method


	public static void main(String [] args)
	{
		new SD2Ass2CloseAccount();
	}
} //end class