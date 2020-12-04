package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler 
{
	//Write a program to read, write, and append to a file.
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		char userDecision = '-';
		
		System.out.println("Welcome to the File Handler.");
		
		//Loop until user decides to 'q' QUIT
		do 
		{
			//Get the user's decision for what to do
			System.out.println("\nWhat will you do next?");
			System.out.println("Press 'w' to write to a new file.");
			System.out.println("Press 'r' to read an existing file.");
			System.out.println("Press 'a' to append to an existing file.");
			System.out.println("Press 'q' to quit.");
			userDecision = scan.next().charAt(0);
			
			//Switch based on user input
			switch(userDecision)
			{
				case('w'):
					System.out.println("WRITING TO A NEW FILE");
					System.out.println("What is the name of the file you want to write to(include file extensions)?");
					scan.nextLine();
					String writingFile = scan.nextLine();
					
					//Attempt to create the file
					try 
					{
						File myObj = new File(writingFile);
						
						//Creates the file or breaks if file already exists
						if (myObj.createNewFile()) 
							System.out.println("File created.");
						else
						{
							System.out.println("File already exists. Please try again.");
							break;
						}
					} 
					catch (IOException e)
					{
						System.out.println("An error occurred creating the file.");
					    return;
					}
					
					//Attempt to write to the file 
					try 
					{
						FileWriter writer = new FileWriter(writingFile);
						
						System.out.println("Type what you want to write to the file. (press enter when done)\n");
						String writing = scan.nextLine();
						
						writer.write(writing);
						writer.close();
					} 
					catch (IOException e) 
					{
						System.out.println("An error occurred writing to the file.");
					}
					break;
					
				case('r'):
					System.out.println("READING AN EXISTING FILE FILE");
					System.out.println("What is the name of the file you want to read(include file extensions)?");
					scan.nextLine();
					String readingFile = scan.nextLine();
					
					//Try to open the file and read it line by line if it exists
					try 
					{
						File myObj = new File(readingFile);
						Scanner myReader = new Scanner(myObj);
						
						System.out.println("The file exists and says:");
						while (myReader.hasNextLine()) 
						{
					        String data = myReader.nextLine();
					        System.out.println(data);
						}
						myReader.close();
					} 
					catch (FileNotFoundException e) 
					{
						System.out.println("An error occurred opening the file.");
					}
					
					break;
				case('a'):
					System.out.println("APPENDING TO AN EXISTING FILE");
					System.out.println("What is the name of the file you want to append to(include file extensions)?");
					scan.nextLine();
					String appending = scan.nextLine();
					
					FileOutputStream outputStream = null;
					File fileTest = new File(appending);
					
					//Check if file exists before attempting to append to it
					if (!fileTest.exists())
					{
						System.out.println("That file does not exist.");
						break;
					}
					
					//Try to create outputstream
					try
					{
						outputStream = new FileOutputStream(appending, true);
					} 
					catch (FileNotFoundException e)
					{
						System.out.println("Invalid file name. Please try again.");
					}
					
					System.out.println("Type what you want to append to the file. (press enter when done)\n");
					String writing = scan.nextLine();
					byte[] writingBytes = writing.getBytes();
					
					//If the output stream was successful, attempt to append to the end of what is already written
					if (outputStream != null)
					{
						try
						{
							outputStream.write(writingBytes);
							outputStream.close();
						} 
						catch (IOException e)
						{
							System.out.println("Problem appending to file.");
						}
					}
					
					break;
				case('q'):
					System.out.println("\nQUITTING\n");
					break;
			}
		}
		while(userDecision != 'q');
		
		scan.close();
	}
}
	