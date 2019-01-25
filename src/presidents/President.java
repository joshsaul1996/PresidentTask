package presidents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class President {
	
	
	    public static void main(String args[]) throws Exception
	    {
	       //Scanner scanner = null;
	       BufferedReader buffReader = null;
	       Calendar cal = null;
	       List<PresidentInfo> presidents = new ArrayList<PresidentInfo>();
	       int minYear = Integer.MAX_VALUE;
	       int maxYear = Integer.MIN_VALUE;
	       int lineNumber = 0;
	       int yearWithMostPresidents = 0;
	       try
	       {
	         
	          buffReader = new BufferedReader(new FileReader(""));
	          
	          String currLine = null;
	          
	          cal = Calendar.getInstance();
	          
	          SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
	          
	          buffReader.readLine();
	          lineNumber++;
	          
	          while( (currLine = buffReader.readLine()) != null)
	          {
	              lineNumber++;
	              
	              String[] words = currLine.split(",");
	              
	              if(words != null){
	                  
	                  if(isNullOrEmpty(words[0]) || isNullOrEmpty(words[1])){
	                      
	                    throw new Exception("Invalid input at line : " + lineNumber);
	                  }
	                  
	                  String name = words[0] != null ? words[0].trim() : null;
	                  
	                  Date birthDate = sdf.parse(words[1].trim(),new ParsePosition(0));
	                  Date deathDate = null;
	                  if(! isNullOrEmpty(words[3])){  
	                    deathDate = sdf.parse(words[3].trim(), new ParsePosition(0));
	                  }else{
	                      deathDate = new Date(); 
	                  
	                  cal.setTime(birthDate);
	                  int birthYear = cal.get(Calendar.YEAR);
	                  
	                  cal.setTime(deathDate);
	                  int deathYear = cal.get(Calendar.YEAR);
	                  
	                  if(birthYear < minYear){
	                      minYear = birthYear;
	                  }
	                  
	                  if(deathYear > maxYear){
	                      maxYear = deathYear;
	                  }
	                  
	                  presidents.add(new PresidentInfo(name,birthYear,deathYear));
	                  
	              }
	          }
	          
	          int[] noOfPresidents = new int[maxYear - minYear + 1];
	          
	          if(presidents != null){
	              for(PresidentInfo president : presidents){
	                  for(int currentYear = president.getBirthYear() - minYear ; currentYear <= president.getDeathYear() - minYear ; currentYear++){
	                      
	                      noOfPresidents[currentYear]++;
	                      
	                      if(noOfPresidents[currentYear] > noOfPresidents[yearWithMostPresidents]){
	                         yearWithMostPresidents = currentYear;
	                      }
	                  }
	              }
	              
	              for(int i = 0 ; i < noOfPresidents.length ; i++){
	                  
	                
	                  
	                  if(noOfPresidents[i] == noOfPresidents[yearWithMostPresidents]){
	                      System.out.println(i+minYear);
	                  }
	              }
	          }
	          }
	       }
	         
	       
	          catch(Exception e)
	       {
	           e.printStackTrace();
	       }finally
	       {
	           if(buffReader != null)
	           {
	               try{
	                   buffReader.close();
	               }catch(Exception e){
	                   e.printStackTrace();
	               }
	           }
	       }
	    }
	    
	    public static boolean isNullOrEmpty(String word){
	        
	        if(word == null || word.trim().length() == 0){
	            return true;
	        }
	        
	        return false;
	    }
	}

	class PresidentInfo{
	    
	    private String name;
	    private int birthYear;
	    private int deathYear;
	    
	    public PresidentInfo(String name,int birthYear, int deathYear)
	    {
	        this.name = name;
	        this.birthYear = birthYear;
	        this.deathYear = deathYear;
	    }
	    
	    public int getBirthYear(){
	        return this.birthYear;
	    }
	    
	    public int getDeathYear(){
	        return this.deathYear;
	    }
	    
	    public String getName(){
	        return this.name;
	    }
}
