import java.text.*;
import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class blog
{
  public static void main(String args[])
  {
    ArrayList<String> paragraph = new ArrayList<String>();
    ArrayList<String> homepage = new ArrayList<String>();
    ArrayList<String> agencyPage = new ArrayList<String>();
    ArrayList<String> categoryPage = new ArrayList<String>();
    Scanner in = new Scanner (System.in);

    int i = 1;
    int listBefore = 0;
    String agencyChoice = "no data";
    String commentExist = "no data";
    String editChoice = "no data";
    String input = "no data";
    String picInput = "no data";
    String picCaption = "no data";
    String agencyCode = "no data";
    String agencyName = "no data";
    String agencyState = "no data";
    String title = "no data";
    String shortTitle = "no data";
    String year = "no data";
    String month = "no data";
    String date = "no data";
    String commentName = "no data";
    String commentDate = "no data";
    String commentTime = "no data";
    String commentText = "no data";

    System.out.print("Add post or comment: ");
    editChoice = in.nextLine();

    // if (editChoice == 1)
    if (editChoice.equals("post"))
    {
      // new post

      System.out.print("Post title: "); // 301: Rural Ride
      title = in.nextLine();
      System.out.print("Post short name: "); // ripta-301
      shortTitle = in.nextLine();

      SimpleDateFormat yM = new SimpleDateFormat("yyyy-MM"); // 2022-07
      Date date1 = new Date();
      String yearMonth = yM.format(date1);
      SimpleDateFormat fD = new SimpleDateFormat("MMMMM d, yyyy"); // July 11, 2022
      Date date2 = new Date();
      String fullDay = fD.format(date2);
      SimpleDateFormat dT = new SimpleDateFormat("MMM d, yyyy HH:mm"); // Jul 11, 2022 01:28
      Date date3 = new Date();
      String dateTime = dT.format(date3);
      SimpleDateFormat t = new SimpleDateFormat("HH:mm"); // 01:28
      Date date4 = new Date();
      String time = dT.format(date4);

      System.out.println("Posting Date: " + fullDay);
      System.out.println("Posting Time: " + time);

      while (!input.equals("0"))
      {
        System.out.println("Enter paragraph " + i + ":");
        input = in.nextLine();

        if (input.equals("0") || input.equals("p"))
        {
          // do nothing
        }
        else
        {
          paragraph.add("<p>" + input + "</p>"); // add if not equal 0 or p
        }

        if (input.equals("p")) // image support
        {
          System.out.print("Enter file name: ");
          picInput = in.nextLine();
          System.out.print("Enter image caption: ");
          picCaption = in.nextLine();

          paragraph.add("<p><img src=../images/" + picInput + " width=500></img><br>");
          paragraph.add(picCaption + "</p>");
        }

        if (!input.equals("p"))
        {
          i++; // paragraph counter
        }
        System.out.println(); // space in editor
      }

      try
      {
        File newFile1 = new File("" + yearMonth + "/" + shortTitle + ".html"); // 2022-07/ripta-301.html
        FileWriter fileWriter1 = new FileWriter(newFile1);

        fileWriter1.write("<title>" + title + " - On the Rails</title> \n");
        fileWriter1.append("<meta name=\"twitter:card\" content=\"summary_large_image\">");
        fileWriter1.append("<meta property=\"twitter:site\" content=\"@itsbenchase\"/>");
        fileWriter1.append("<meta property=\"og:title\" content=\"" + title + " - On the Rails\"/>");
        fileWriter1.append("<meta property=\"og:description\" content=\"On the Rails is a transit blog where I critique the planning of routes and stops.\"/>");
        fileWriter1.append("<meta property=\"og:url\" content=\"https://transit.benchase.info/" + year + "-" + month + "/" + shortTitle + ".html\"/>");
        fileWriter1.append("<meta property=\"og:image\" content=\"http://transit.benchase.info/images/embed-img.png\"/>");
        fileWriter1.append("<link rel=stylesheet href=../style.css>");
        fileWriter1.append("<ul><li><a href=../index.html>Home</a></li>");
        fileWriter1.append("<li><a href=../about.html>About the Editor</a></li>");
        fileWriter1.append("<li><a href=../categories.html>Categories</a></li></ul>");
        fileWriter1.append("<h1>" + title + "</h1> \n");
        fileWriter1.append("<h3>" + fullDay + "</h3> \n");
        fileWriter1.append(paragraph.get(0) + "\n");

        for (int j = 1; j < paragraph.size(); j++)
        {
          fileWriter1.append(paragraph.get(j) + "\n");
        }

        fileWriter1.append("<b>Posted:</b> " + dateTime);
        fileWriter1.close();

        System.out.println("Post saved.");
      }
      catch (IOException e)
      {
          System.out.println("Error saving post. Please try again.");
      }

      // add to homepage

      try
      {
        Scanner s = new Scanner(new File("index.html"));

        while (s.hasNextLine())
        {
          homepage.add(s.nextLine());
        }
        s.close();
      }
      catch (FileNotFoundException e)
      {
      }

      homepage.add(16, "<a href=" + yearMonth + "/" + shortTitle + ".html>" + title + " - " + date + "</a><br>");

      try
      {
        File newFile2 = new File("index.html");
        FileWriter fileWriter2 = new FileWriter(newFile2);

        fileWriter2.write(homepage.get(0) + "\n");

        for (int j = 1; j < homepage.size(); j++)
        {
          fileWriter2.append(homepage.get(j) + "\n");
        }
        fileWriter2.close();
      }
      catch (IOException e)
      {
      }

      // categorize

      System.out.print("Enter agency code: "); // ri-ripta
      agencyCode = in.nextLine();
      System.out.print("Enter agency name: "); // Rhode Island Public Transit Authority
      agencyName = in.nextLine();
      System.out.print("Enter agency state: "); // Rhode Island
      agencyState = in.nextLine();
      System.out.print("Does category exist? "); // yes
      agencyChoice = in.nextLine();

      if (agencyChoice.equals("yes"))
      {
        try
        {
          Scanner s = new Scanner(new File("posts-" + agencyCode + ".html"));

          int k = 1;
          while (s.hasNextLine())
          {
            agencyPage.add(s.nextLine());
            System.out.println(k + ". " + agencyPage.get(k - 1));
            k++;
          }
          s.close();
        }
        catch (FileNotFoundException e)
        {
        }

        System.out.print("Listing before: ");
        listBefore = in.nextInt();
        agencyPage.add(listBefore, "<a href=" + yearMonth + "/" + shortTitle + ".html>" + title + " - " + date + "</a><br>");
      }
      else
      {
        agencyPage.add("<link rel=stylesheet href=style.css>");
        agencyPage.add("<ul><li><a href=index.html>Home</a></li>");
        agencyPage.add("<li><a href=about.html>About the Editor</a></li>");
        agencyPage.add("<li><a class=active href=categories.html>Categories</a></li></ul>");
        agencyPage.add("<meta name=\"twitter:card\" content=\"summary_large_image\">");
        agencyPage.add("<meta property=\"twitter:site\" content=\"@itsbenchase\"/>");
        agencyPage.add("<meta property=\"og:title\" content=\"" + agencyName + " - " + agencyState + "- On the Rails\"/>");
        agencyPage.add("<meta property=\"og:description\" content=\"On the Rails is a transit blog where I critique the planning of routes and stops.\"/>");
        agencyPage.add("<meta property=\"og:url\" content=\"https://transit.benchase.info/posts-" + agencyCode + ".html\"/>");
        agencyPage.add("<meta property=\"og:image\" content=\"http://transit.benchase.info/images/embed-img.png\"/>");
        agencyPage.add("<title>" + agencyName + " - " + agencyState + " - On the Rails</title> \n");
        agencyPage.add("<h1>" + agencyName + " - " + agencyState + "</h1>");
        agencyPage.add("<p>");
        agencyPage.add("<a href=" + yearMonth + "/" + shortTitle + ".html>" + title + " - " + date + "</a><br>");
        agencyPage.add("</p>");

        try
        {
          Scanner s = new Scanner(new File("categories.html"));

          int n = 1;
          while (s.hasNextLine())
          {
            categoryPage.add(s.nextLine());
            System.out.println(n + ". " + categoryPage.get(n - 1));
            n++;
          }
          s.close();
        }
        catch (FileNotFoundException e)
        {
        }

        System.out.print("Listing before: ");
        listBefore = in.nextInt();
        categoryPage.add(listBefore, "<a href=posts-" + agencyCode + ".html>" + agencyName + " - " + agencyState + "</a><br>");

        try
        {
          File newFile3 = new File("categories.html");
          FileWriter fileWriter3 = new FileWriter(newFile3);

          fileWriter3.write(categoryPage.get(0) + "\n");

          for (int j = 1; j < categoryPage.size(); j++)
          {
            fileWriter3.append(categoryPage.get(j) + "\n");
          }
          fileWriter3.close();
        }
        catch (IOException e)
        {
        }
      }

      try
      {
        File newFile2 = new File("posts-" + agencyCode + ".html");
        FileWriter fileWriter2 = new FileWriter(newFile2);

        fileWriter2.write(agencyPage.get(0) + "\n");

        for (int j = 1; j < agencyPage.size(); j++)
        {
          fileWriter2.append(agencyPage.get(j) + "\n");
        }
        fileWriter2.close();
      }
      catch (IOException e)
      {
      }
    }

    if (editChoice.equals("comment"))
    {
      // new comment

      System.out.print("Enter post year: ");
      year = in.nextLine();
      System.out.print("Enter post month: ");
      month = in.nextLine();
      System.out.print("Enter post short name: ");
      shortTitle = in.nextLine();

      // import post page
      try
      {
        Scanner s = new Scanner(new File("" + year + "-" + month + "/" + shortTitle + ".html"));

        while (s.hasNextLine())
        {
          paragraph.add(s.nextLine());
        }
        s.close();
      }
      catch (FileNotFoundException e)
      {
      }

      System.out.print("Does section exist? ");
      commentExist = in.nextLine();
      if (commentExist.equals("no"))
      {
        paragraph.add("<h3>Comments</h3>");
      }

      System.out.print("Name: ");
      commentName = in.nextLine();
      System.out.print("Date: ");
      commentDate = in.nextLine();
      System.out.print("Time: ");
      commentTime = in.nextLine();
      System.out.print("Comment: ");
      commentText = in.nextLine();

      paragraph.add("<p><b>" + commentName + " on " + commentDate + " at " + commentTime + ":</b> " + commentText);
      System.out.println("Comment added.");

      try
      {
        File newFileE = new File("" + year + "-" + month + "/" + shortTitle + ".html");
        FileWriter fileWriterE = new FileWriter(newFileE);

        fileWriterE.write(paragraph.get(0) + "\n");

        for (int j = 1; j < paragraph.size(); j++)
        {
          fileWriterE.append(paragraph.get(j) + "\n");
        }
        fileWriterE.close();
      }
      catch (IOException e)
      {
      }
    }
  }
}