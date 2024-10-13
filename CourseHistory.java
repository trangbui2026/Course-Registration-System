import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

//NOTES:  You will need the import statements that appear at the top of this file, so you should
//leave them in place.  Follow the list of steps on the project writeup to complete the CourseHistory
//class that is started below.  This entire block of comments should be deleted when you are done. 

// Tracy Bui - Project 3
public class CourseHistory
{
    private ArrayList<CompletedCourse> courseList;
    public CourseHistory() {
        courseList = new ArrayList<CompletedCourse>();
        String department;   
        String courseNumber;
        String semesterTaken; 
        String credit;       
        String grade;          
        String competency;    
        String distArea;
        try
        {
            FileReader reader = new FileReader("finishedcourses.txt");
            Scanner in = new Scanner(reader);

            while(in.hasNextLine())   
            {  department = in.nextLine();  
                courseNumber = in.nextLine();
                semesterTaken = in.nextLine();
                credit = in.nextLine();  
                grade = in.nextLine();
                competency = in.nextLine();
                distArea = in.nextLine();  
                CompletedCourse theCourse = new CompletedCourse(department, courseNumber, semesterTaken, credit, grade, competency, distArea);
                courseList.add(theCourse); 

            }
            in.close();  //Close the file when we are done reading it
        } catch (IOException exception)
        {
            System.out.println("Error processing file: " + exception);
        }   
    }

    public void displayCourseHistory()
    {
        System.out.println("---------Course History---------");
        // Display all courses
        for (int i=0; i<courseList.size(); i++)
        {
            courseList.get(i).displayCourse();
        }
    }

    public void summaryReport()
    {
        System.out.println("---------Summary Report---------");
        double totalcredits = 0;
        double totalgrades = 0;
        for (int i=0; i<courseList.size(); i++)
        {   
            // Calculating the total credits and grades
            if (courseList.get(i).getGrade() > 0)
            {
                totalcredits += courseList.get(i).getCredit(); 
                totalgrades += courseList.get(i).getCredit()*courseList.get(i).getGrade();
            }
        }
        System.out.println("Total credit is: " + totalcredits);
        System.out.println("Total grade is: " + totalgrades);
    }

    public void distAreaReport()
    {
        System.out.println("---------Distribution Area Report---------");
        // Variables to store the total credit
        double totalAH = 0;
        double totalSS = 0;
        double totalSM = 0;
        double totalLA = 0;
        for (int i=0; i<courseList.size(); i++)
        {
            if (courseList.get(i).getGrade()>0 && courseList.get(i).getDistArea().equals("AH"))
            {
                totalAH += courseList.get(i).getCredit(); 
            }
            if (courseList.get(i).getGrade()>0 && courseList.get(i).getDistArea().equals("SS"))
            {
                totalSS += courseList.get(i).getCredit(); 
            }
            if (courseList.get(i).getGrade()>0 && courseList.get(i).getDistArea().equals("SM"))
            {
                totalSM += courseList.get(i).getCredit(); 
            }
            if (courseList.get(i).getGrade()>0 && courseList.get(i).getDistArea().equals("LA"))
            {
                totalLA += courseList.get(i).getCredit(); 
            }
        }
        System.out.println("Number of credits completed for the AH: " + totalAH);
        System.out.println("Number of credits completed for the SS: " + totalSS);
        System.out.println("Number of credits completed for the SM: " + totalSM);
        System.out.println("Number of credits completed for the LA: " + totalLA);
    }

    public void compReport()
    {
        System.out.println("---------Competency RSTeport---------");
        // Declare boolean value to check if at least one course of a competency is fullfiled
        boolean countW = false; 
        boolean countQ = false;
        boolean countS = false;
        for (int i=0; i<courseList.size(); i++)
        {   
            // No matter how many times the course of a comptency is fulfilled, the boolean value will be true if at least one course is fulfilled
            if (courseList.get(i).getGrade()>0)
            {
                if(courseList.get(i).getCompetency().equals("W"))
                {
                    countW = true;
                }
                if(courseList.get(i).getCompetency().equals("Q"))
                {
                    countQ = true;
                }
                if(courseList.get(i).getCompetency().equals("S"))
                {
                    countS = true;
                }
            }
        }
        // Check if three competencies are fulfilled
        if ( countW && countQ && countS)
        {
            System.out.println("All competencies completed");
        }
        // Check if none of competencies are fulfilled
        else if (!countW && !countQ && !countS)
        {
            System.out.println("No competencies completed");
        }
        else 
        {
            System.out.println("Competencies Partially Completed");
            // Check the condition of each competency
            if (countW) 
                System.out.println("W completed");
            else 
                System.out.println("W incompleted");

            if (countQ) 
                System.out.println("Q completed");
            else 
                System.out.println("Q incompleted");

            if (countS) 
                System.out.println("S completed");
            else 
                System.out.println("S incompleted");
        }
    }

    public void fullReport()
    {
        System.out.println("---------Full Report---------");
        summaryReport();
        distAreaReport();
        compReport();
    }

    public void sortListGPA()
    {        
        int maxIndex;
        ArrayList<CompletedCourse> tempCourseList = new ArrayList<CompletedCourse>();
        // Copy the elements in the courseList to the tempCourseList so that the original courseList is not influenced    
        for (int i = 0; i < courseList.size(); i++) {
            tempCourseList.add(courseList.get(i));
        }

        for (int i=0; i<tempCourseList.size(); i++)
        { 
            maxIndex = i;
            for (int j=i+1; j<tempCourseList.size(); j++)
            {
                if(tempCourseList.get(j).getGrade() > tempCourseList.get(maxIndex).getGrade())
                {
                    // Finding the largest grade that starts from the index i + 1
                    maxIndex = j;
                }
            }
            // Swap the largest grade course with the current course at index i
            CompletedCourse tempCourse= tempCourseList.get(i);
            tempCourseList.set(i,tempCourseList.get(maxIndex));
            tempCourseList.set(maxIndex,tempCourse);
            tempCourseList.get(i).displayCourse();
        }
    }

}

