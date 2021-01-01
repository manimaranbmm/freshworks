import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*; 
public class Freshworks
{ 
private static String filepath=new String(); 
    public String getpath()
    { 
        return Freshworks.filepath; 
    }
      protected void ReadJson(){ 
       Freshworks obj=new Freshworks(); 
       String  filepath1=obj.getpath();    
       System.out.println("Enter the key to read the value"); 
       Scanner sc=new Scanner(System.in); 
       String search=sc.next(); 
       JSONParser jsonParser = new JSONParser(); 
       try(FileReader reader = new FileReader(filepath1))
       { 
            Object obj1 = jsonParser.parse(reader); 
            JSONObject jsonObject = (JSONObject) obj1; 
            String where=jsonObject.get(search).toString(); 
            try
            { 
                System.out.println("Value "+where); 
            }
            catch(Exception e)
            { 
                System.out.println("The Key is not found"); 
            } 
        }
        catch(Exception e){ 
            System.out.println(e); 
        } 
    } 
    protected void CreateJson(){ 
        Freshworks obj=new Freshworks(); 
        String filepath1=obj.getpath(); 
        JSONObject obj1=new JSONObject(); 
        Scanner sc=new Scanner(System.in); 
        String []key=new String[2]; 
            System.out.println("Enter the input as key:value"); 
           String key_pair=sc.nextLine(); 
            key=key_pair.split(":"); 
            if(key.length==2){ 
                obj1.put(key[0],key[1]); 
                } 
            else{ 
                System.out.println("Invalid format");  
            }  
        try{ 
            File file=new File(filepath1); 
            long size=file.length(); 
            System.out.println("Size of the file"+size); 
            double size_of_the_file; 
            if(size>10000){ 
                size_of_the_file=size/1024;
                size_of_the_file=size_of_the_file/1024; 
                size_of_the_file=size_of_the_file/1024; 
                if(size_of_the_file>1){ 
                    System.out.println("File size limit exceeded"); 
                } 
            } 
            PrintWriter pw=new PrintWriter(new File(filepath1)); 
            
                pw.write("{"+"\n"+'"'+key[0]+'"'+":"+'"'+key[1]+'"'+",\n"+"}");
            pw.flush(); 
        }catch(Exception e){ 
            System.out.println("Error"+e); 
        } 
    } 
    public void DeleteJson() throws FileNotFoundException, IOException { 
        Freshworks obj=new Freshworks(); 
        String filepath1=obj.getpath();  
        JSONParser jsonParser = new JSONParser(); 
        try(FileReader reader = new FileReader(filepath1)){ 
            Object obj1 = jsonParser.parse(reader); 
            JSONObject jsonObject = (JSONObject) obj1; 
            System.out.println("before deletion "+jsonObject.toString());
            System.out.println("Enter the key to delete"); 
            Scanner sc=new Scanner(System.in); 
            String del=sc.next(); 
            jsonObject.remove(del); 
            System.out.println("After deletion "+jsonObject.toString()); 
            PrintWriter writer = new PrintWriter(new File(filepath1)); 
            writer.append(jsonObject.toJSONString()); 
            writer.flush(); 
        }catch(Exception e){ 
            System.out.println("Error"+e); 
        } 
    } 
    public static void main(String[] args) throws IOException, FileNotFoundException{ 
        System.out.println("Enter the location: or mention as \"NA\" ( for default location):"); 
        Freshworks obj=new Freshworks(); 
        Freshworks.filepath="CRD.json"; 
        Scanner sc=new Scanner(System.in); 
        String path=sc.nextLine(); 
        if(!(path).equals("NA")) {
            Freshworks.filepath=path;
        } 
        System.out.println("CRD Oprations");
        while(true){ 
            System.out.println(" 1.create \n 2.read\n 3.Delete \n 4.Exit \n"); 
            int choice=sc.nextInt(); 
            switch(choice){ 
                case 1:obj.CreateJson();break; 
                case 2:obj.ReadJson();break; 
                case 3:obj.DeleteJson();break; 
                case 4:System.exit(0);
                default:System.out.println("Invalid Choice");
            } 
        } 
    } 
}