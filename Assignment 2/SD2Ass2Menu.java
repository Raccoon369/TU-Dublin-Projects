// Proframmer: Sarunas Kucys
// Student ID: B00100612
// Date written: 15.04.2018
// Function: Software Development 2 / Assignment 2
// Note: Credit Union Program

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class SD2Ass2Menu extends JFrame
{
	private JButton checkBalance, openAccount, closeAccount, makeLodgement, makeWithdrawal, setNewOverdraft, exit;
	private JPanel labelPanel = new JPanel(), buttonsPanel = new JPanel();

	public SD2Ass2Menu()
	{
        super( "Menu Panel" );

		setLayout( new GridLayout(2, 1));

     	labelPanel.setLayout(new GridLayout(1, 1));
     	labelPanel.setBackground(Color.WHITE);

     	buttonsPanel.setLayout(new GridLayout(7, 1, 5, 5));

     	add(labelPanel);
		add(buttonsPanel);

        JLabel background = new JLabel(new ImageIcon("itbcu.jpg"));
		labelPanel.add(background);
        setVisible(true);

		checkBalance = new JButton(  "Check Account Balance" );
		openAccount = new JButton(  "Open New Account" );
  		closeAccount = new JButton(  "Close Account" );
  		makeLodgement = new JButton(  "Make Lodgement" );
  		makeWithdrawal = new JButton(  "Make Withdrawal" );
		setNewOverdraft = new JButton(  "Set New Overdraft" );
		exit = new JButton(  "Exit" );

		buttonsPanel.add(checkBalance);
		buttonsPanel.add(openAccount);
		buttonsPanel.add(closeAccount);
		buttonsPanel.add(makeLodgement);
		buttonsPanel.add(makeWithdrawal);
		buttonsPanel.add(setNewOverdraft);
		buttonsPanel.add(exit);


		checkBalance.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				new SD2Ass2CheckBalance(); //calling the checkBalance class
			}
		}
		); //closes new ActionListener

		openAccount.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				new SD2Ass2OpenAccount(); //calling the openAccount class
			}
		}
		); //closes new ActionListener


		closeAccount.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				new SD2Ass2CloseAccount(); //calling the closeAccount class
			}
		}
		); //closes new ActionListener


		makeLodgement.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				new SD2Ass2MakeLodgement(); //calling the makeLodgement class
			}
		}
		); //closes new ActionListener


		makeWithdrawal.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				new SD2Ass2MakeWithdrawal(); //calling the makeWithdrawal class
			}
		}
		); //closes new ActionListener


		setNewOverdraft.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				new SD2Ass2SetNewOverdraft(); //calling the setNewOverdraft class
			}
		}
		); //closes new ActionListener


		exit.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				System.exit(0);
			}
		}
		); //closes new ActionListener

		setSize(550, 500);
		setVisible( true );
    }


public static void main(String[] args ) //main method
	{
		new SD2Ass2Menu();
	}
}