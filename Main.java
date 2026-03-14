import java.util.*;

public class SmartCodeDetector 
{

    static int errors = 0;
    static int warnings = 0;

    public static void analyzeCode(List<String> codeLines) 
  {

        System.out.println("\n CODE ANALYSIS REPORT \n");

        int lineNumber = 1;

        for(String line : codeLines) 
        {

            String code = line.trim();

            // Check assignment instead of comparison
          
            if(code.contains("if(") && code.contains("=") && !code.contains("==")) 
            {
                System.out.println("Line " + lineNumber + ": Possible assignment used instead of comparison.");
                System.out.println("Suggestion: Use '==' for comparison.");
                warnings++;
            }

            // Check for possible infinite loop
          
            if(code.contains("while(true)"))
            {
                System.out.println("Line " + lineNumber + ": Possible infinite loop detected.");
                warnings++;
            }

            // Check array length mistake
          
            if(code.contains("<= arr.length")) 
            {
                System.out.println("Line " + lineNumber + ": Risk of ArrayIndexOutOfBounds.");
                System.out.println("Suggestion: Use i < arr.length");
                errors++;
            }

          
            // Check missing semicolon
          
            if((code.startsWith("int ") || code.startsWith("String ") || code.startsWith("double "))
                    && !code.endsWith(";"))
            {
                System.out.println("Line " + lineNumber + ": Missing semicolon.");
                errors++;
            }

            // Check bad variable naming
          
            if(code.matches("int [a-z];")) 
            {
                System.out.println("Line " + lineNumber + ": Poor variable name.");
                System.out.println("Suggestion: Use meaningful variable names.");
                warnings++;
            }

            lineNumber++;
        }

        System.out.println("\n SUMMARY ");

        System.out.println("Errors Found: " + errors);
        System.out.println("Warnings Found: " + warnings);

        int score = 100 - (errors * 15 + warnings * 5);

        if(score < 0) score = 0;

        System.out.println("Code Quality Score: " + score + "/100");

        System.out.println("\n SUGGESTIONS ");

        if(score > 80) 
        {
            System.out.println("Excellent code quality!");
        } else if(score > 50) {
            System.out.println("Good code but needs improvements.");
        } else {
            System.out.println("Code quality is low. Fix errors and improve coding practices.");
        }
    }

    public static void main(String[] args) 
  {

        Scanner sc = new Scanner(System.in);

        List<String> codeLines = new ArrayList<>();

        System.out.println("===== SMART CODE ERROR DETECTOR =====");
        System.out.println("Enter your Java code line by line.");
        System.out.println("Type 'END' to finish.\n");

        while(true) 
        {

            String line = sc.nextLine();

            if(line.equalsIgnoreCase("END")) 
            {
                break;
            }

            codeLines.add(line);
        }

        analyzeCode(codeLines);

        sc.close();
    }
}
