//This project is done by Yong Jia Liang, UOW ID 7083609
//All codes in this file is my own with some of the formats which can be found online.
//i.e. replace("[","{").
//I did not pass my work to any friend.
//I am willing to accept whatever penalty given to me.

import java.util.*;
import java.util.Scanner;

enum Month
{
	Jan(1, "January"),
	Feb(2, "February"),
	Mar(3, "March"),
	Apr(4, "April"),
	May(5, "May"),
	Jun(6, "June"),
	Jul(7, "July"),
	Aug(8, "August"),
	Sep(9, "September"),
	Oct(10, "October"),
	Nov(11, "November"),
	Dec(12, "December");
	
	private int numMonth;
	private String wordMonth;
	
	Month(int numMonth, String wordMonth)
	{
		this.numMonth = numMonth;
		this.wordMonth = wordMonth;
	}
	
	public int getNum()
	{
		return numMonth;
	}
	
	public String getWord()
	{
		return wordMonth;
	}
}//end enum

class Set
{
	private ArrayList <Month> s = new ArrayList <Month> ();
	
	public Set() //initiates Set arraylist
	{
		this.s = new ArrayList <Month> ();
	}
	
	public Set(Set otherSet)
	{
		this.s = new ArrayList <Month> ();
		otherSet.s = new ArrayList <Month> ();
	}
	
	public boolean isEmpty() //check if Set is empty
	{
		return s.isEmpty();
	}
	
	public int cardinality() //check how many elements in set
	{
		return s.size();
	}
	
	public boolean belongTo(Month element)//check if element is in the set
	{
		return s.contains(element);
	}
	
	public void addElement(Month element) //add the element into the set
	{
		if (!belongTo(element)) //checks if the element is already in the set
		{
			s.add (element);
		}
	}
	
	public boolean subset(Set otherSet) //check if elements of set are in otherSet
	{
		boolean check = true;
		for (int i = 0; check == true && i < s.size(); i++)
		{
			check = otherSet.belongTo(s.get(i));
		}
		return check;
	}
	
	public void union(Set otherSet) //A+B (no repeats)
	{
		ArrayList <Month> temp1 = s;
		ArrayList <Month> temp2 = otherSet.s;
		temp2.removeAll(temp1); //remove all repeated elements
		temp1.addAll(temp2); //adds all elements without repetition
		System.out.println("\n\tUnion of A and B = " + temp1.toString().replace("[", "{").replace("]", "}"));
	}
	
	public void intersection(Set otherSet) //elements in both A and B
	{
		ArrayList <Month> temp = new ArrayList <Month> ();
		for (int i = 0; i < s.size(); i++)
		{
			if (otherSet.belongTo(s.get(i))) //checks if elements of set are also in otherSet
			{
				temp.add (s.get(i)); //adds element to temp set
			}
		}
		System.out.println("\n\tIntersection of A and B = " + temp.toString().replace("[", "{").replace("]", "}"));
	}
	
	public void difference(Set otherSet) //A-B
	{
		ArrayList <Month> temp = s;
		for (int i = 0; i < otherSet.s.size(); i++)
		{
			if (belongTo(otherSet.s.get(i))) //checks if elements of otherSet are also in set
			{
				temp.remove(otherSet.s.get(i)); //removes the element belonging in both sets
			}
		}
		System.out.println("\n\tA - B = " + temp.toString().replace("[", "{").replace("]", "}"));
	}
	
	public Set complement() //Universal set - A = A'
	{
		Set setA = new Set();
		setA.s = new ArrayList <Month> (EnumSet.allOf(Month.class)); //create set containing all Month elements, aka Universal set
		setA.s.removeAll(s); //Universal set - set
		return setA;
	}
	
	public boolean equality(Set otherSet) //check if A is a subset of B and if B is a subset of A
	{
		boolean check = true;
		for (int i = 0; check == true && i < s.size(); i++)
		{
			check = otherSet.belongTo(s.get(i));
		}
		for (int i = 0; check == true && i < otherSet.s.size(); i++)
		{
			check = belongTo(otherSet.s.get(i));
		}
		return check;
	}
	
	public String toString()
	{
		return String.format("%s", s).replace("[", "{").replace("]", "}");
	}
	
	public String getString_1()
	{
		String str = "";
		for (Month t : s)
		{
			str = String.format("%s%02d, ", str, t.getNum());
		}
		return str;
	}
	
	public String getString_2()
	{
		String str = "";
		for (Month t : s)
		{
			str = String.format("%s%s, ", str, t.getWord());
		}
		return str;
	}
}//end Set class

class a1
{
	private static Scanner input = new Scanner(System.in);
	
	private static void displayMonthInfo() //display all Month info
	{
		System.out.println("\nUniversal set info\n");
		for (Month s : Month.values())
		{
			System.out.printf("%s  %02d \t %10s%n", s, s.getNum(), s.getWord()); //return Month info
		}
	}
	
	private static Month getAnElement() //returns Month element
	{
		Random r = new Random();
		int number = r.nextInt(12); //generates random number 0-11 for indexing the Month enum
		Month element = Month.values()[number];
		return element;
	}
	
	private static Set getASet() //creates a set when method is called
	{
		Set setObj = new Set();
		Random r = new Random(); 
		int number = r.nextInt(12); //random number of months in a set
		int i = 0;
		do
		{
			setObj.addElement(getAnElement());
			i++;
		} while (setObj.isEmpty() == true || i < number);
		//check if setObj is empty. There are ways to remove this do loop but if not here i don't know where else to use isEmpty()
		return setObj;
	}
	
	private static void displayMenu() //displays main menu
	{
		System.out.println("\n------------------------------------------\n");
		System.out.println("Welcome to SIM Set Theory lesson\n");
		System.out.println("0: Properties of set");
		System.out.println("1: Union example");
		System.out.println("2: Intersection example");
		System.out.println("3: Subset example");
		System.out.println("4: Difference example");
		System.out.println("5: Complement example");
		System.out.println("6: Sets equality example");
		System.out.println("9: Quit\n");
		System.out.print("Your option: ");
	}
	
	public static void displaySubMenu() //option 0 - displays submenu
	{
		System.out.println("\nSome basic operations in set");
		System.out.println("\t1: Add an element");
		System.out.println("\t2: Check an element");
		System.out.println("\t3: Cardinality");
		System.out.println("\t4: Various displays");
		System.out.println("\t9: Quit\n");
		System.out.print("Your option: ");
	}
	
	private static void unionExample() //option 1
	{
		Set setA = new Set();
		Set setB = new Set();
		setA = getASet();
		setB = getASet();
		
		System.out.println("\nGiven Sets\n\tA = " + setA);
		System.out.println("\tB = " + setB);
		setA.union(setB);
	}
	
	private static void intersectionExample() //option 2
	{
		Set setA = new Set();
		Set setB = new Set();
		setA = getASet();
		setB = getASet();
		
		System.out.println("\nGiven Sets\n\tA = " + setA);
		System.out.println("\tB = " + setB);
		setA.intersection(setB);
	}
	
	private static void subsetExample() //option 3
	{
		Set setA = new Set();
		Set setB = new Set();
		setA = getASet();
		setB = getASet();
		
		System.out.println("\nGiven Sets\n\tA = " + setA);
		System.out.println("\tB = " + setB);
		
		boolean checkA = setA.subset(setB);
		System.out.println("\nConclusion\n\tA subset of B: " + checkA);
		
		boolean checkB = setB.subset(setA);
		System.out.println("\tB subset of A: " + checkB);
	}
	
	private static void differenceExample() //option 4
	{
		Set setA = new Set();
		Set setB = new Set();
		setA = getASet();
		setB = getASet();
		
		System.out.println("\nGiven Sets\n\tA = " + setA);
		System.out.println("\tB = " + setB);
		setA.difference(setB);
	}
	
	private static void complementExample() //option 5
	{
		Set setA = new Set();
		setA = getASet();
		
		System.out.println("\tA = " + setA);
		System.out.println("\n\tA' = " + setA.complement());
	}
	
	private static void equalityExample() //option 6
	{
		Set setA = new Set();
		Set setB = new Set();
		setA = getASet();
		setB = getASet();
		
		System.out.println("\nGiven Sets\n\tA = " + setA);
		System.out.println("\tB = " + setB);
		
		boolean checkA = setA.subset(setB);
		System.out.println("\nAnalysis\n\tA subset of B: " + checkA);
		
		boolean checkB = setB.subset(setA);
		System.out.println("\tB subset of A: " + checkB);
		
		boolean checkE = setA.equality(setB);
		System.out.println("\nConclusion\n\tA equals to B: " + checkE);
		
	}
	
	public static void anExample()
	{
		Set setA = new Set();
		setA = getASet();
		System.out.println("\nHere is an example of set");
		System.out.print("\tA = " + setA);
		System.out.println("\n\tAll elements in set are distinct and random order");
		
		int secondUserInput = 0;
		do
		{
			displaySubMenu();
			secondUserInput = input.nextInt();
			if (secondUserInput == 1) //add an element to the set
			{
				System.out.print("\nEnter an element: ");
				input.nextLine();
				
				String ele = input.nextLine();
				Month element = Month.valueOf(ele);
				setA.addElement(element);
				System.out.println("A = " + setA);
				System.out.println("------------------------------------------");
			}
			else if (secondUserInput == 2) //check if an element is in the set
			{
				System.out.print("\nEnter an element: ");
				input.nextLine();
				
				String ele = input.nextLine();
				Month element = Month.valueOf(ele);
				if (setA.belongTo(element) == true)
				{
					System.out.println("Element " + ele + " is in set");
				}
				else
				{
					System.out.println("Element " + ele + " is not in set");
				}
				System.out.println("------------------------------------------");
			}
			else if (secondUserInput == 3) //cardinality, number of elements in the set
			{
				int cardin = setA.cardinality();
				System.out.println("\nNo of elements in set is " + cardin);
				System.out.println("------------------------------------------");
			}
			else if (secondUserInput == 4) //various displays
			{
				variousDisplays(setA);
				System.out.println("------------------------------------------");
			}
			else if (secondUserInput == 9)
			{
			}
			else 
			{
				System.out.println("\nPlease enter a valid option.");
			}
		} while (secondUserInput != 9);
	}
	
	private static void variousDisplays(Set s)
	{
		System.out.println("\nEquivalent sets info");
		System.out.println("\tSet 1: {" + s.getString_1() + "\b\b}");
		System.out.println("\tSet 2: {" + s.getString_2() + "\b\b}");
	}
	
	public static void main(String[] args)
	{
		displayMonthInfo();
		int userInput;
		do
		{
			displayMenu();
			
			userInput = input.nextInt();
			if (userInput == 0)
			{
				anExample();
			}
			else if (userInput == 1)
			{
				unionExample();
			}
			else if (userInput == 2)
			{
				intersectionExample();
			}
			else if (userInput == 3)
			{
				subsetExample();
			}
			else if (userInput == 4)
			{
				differenceExample();
			}
			else if (userInput == 5)
			{
				complementExample();
			}
			else if (userInput == 6)
			{
				equalityExample();
			}
			else if (userInput == 9)
			{
				System.out.println("\nThank you for learning.");
			}
			else
			{
				System.out.println("\nPlease enter a valid option.");
			}
		} while (userInput != 9);
	}
}//end main class