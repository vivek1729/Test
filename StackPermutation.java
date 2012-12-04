import java.io.*;
import java.util.Scanner;
public class StackPermutation {
    
    private int [] stack;
    private int tail;
    private StackPermutation(int size) {
       stack = new int[size];
       tail = -1; 
    }
    
    public void push(int n){
        tail++;
        stack[tail]=n;
    
    }
    
    public int pop(){
        if(tail == -1)
          return 0;
        else
         return stack[tail--];   
    }
    //=======================================================================
    public static void main(String[] args){
       int choice = Integer.parseInt(args[0]);
       int size_input = 0;
       int i,j=0; 
       int top = 0;
       int num_entered;
       int [] permut;
       boolean flag=true;
      
       Scanner input = new Scanner(System.in);
       if(choice == 1){
       //For standard input
        j=0;
        System.out.println("Enter the size of stack permutation");
        num_entered = input.nextInt();
        size_input = num_entered;
        permut = new int[size_input];
        if(size_input<=0){
            System.out.println("Wrong input: The size of the stack must be positive.");
            System.exit(0);//exit the program
        }
        System.out.println("Enter the "+num_entered+" numbers:");
        for(i=0;i<num_entered;i++){
            permut[j] = input.nextInt();          
            j++;
            //now the permut array is filled with the numbers passed from the command line.
        }
       
       input.close(); //Close the scanner after the numbers are input
       }
       else if(choice ==2){
        //For file input
        j=0;
        try{
                File file = new File(args[1]);
                Scanner scanner = new Scanner(file);
                //Read File Line By Line
                //read the first line to get the size
                
                size_input = scanner.nextInt();
					if(size_input<=0){
							System.out.println("Wrong input: The size of the stack must be positive.");
							System.exit(0);//exit the program
							}
                permut = new int[size_input];
  
                //Now it will start reading from the second line.
                while (scanner.hasNextInt())   {
                // Update the contents into an integer array permut
                        if(j==size_input){
                            top = 1;
                            break;}
                permut[j] = scanner.nextInt();
                j++;
                 }
        
  
             //Close the input stream
             scanner.close();
        }
        catch(Exception e){
               permut = new int[0]; //initialise permut[]
               System.out.println("Error: "+e.getMessage());
               System.exit(0);       
        }
   
       }
       else{
       permut = new int[0];
       System.out.println("Error: Wrong input format.");
       flag = false;
       }
       
 //=========================================================================
//Input validation       
       String error = "";
       
       if(top==1){
           flag = false;
           error= "Wrong Input: Size of entries is larger than declared input size.";
       }
       for(i=0;i<permut.length;i++){
        if(permut[i]<=permut.length&&permut[i]>=1){ //1<=permut[i]<=top
            for(j=0;j<i;j++){
                 if(permut[i]==permut[j])
                 {   
                     //input is invalid
                     error = "Wrong Input: One or more entries are same.";
                     flag=false;
                     break;
                 }
            }
        }
        else{
        //input is invalid
         flag=false;
            if(permut[i]>permut.length)
                error = "Wrong Input: One or more entries are out of bound(greater than n).";
            else
                error = "Wrong Input: Some entries are missing or are set to 0.";
         break;
        }
       
       }      
  //========================================================================
       
   top = permut.length;
   int [] ref = new int[top];
   for(i=0;i<top;i++){
       ref[i] = i+1;
   } 
   
   //========================================================================
   StackPermutation inst = new StackPermutation(size_input);
   int k=0;
   int current=0;
   String steps = "Yes\n";
    
   if(!flag)
           System.out.println(error);
   else{
   for(i=0;i<top;i++){
          //System.out.println(i);   
            if(permut[i] == ref[current]){
                steps+= "print()\n";
                if(current<(top-1))
                  current++;
                //System.out.println("current"+current);
            
            }
            else if(permut[i] > ref[current]){
              //ref[current]+1 to permut[i] all are pushed  
              for(k=ref[current];k<permut[i];k++){
                inst.push(k);
                steps+= "push("+k+")"+"\n";
                current++;
              }
              if(ref[current] != top)
              { 
                 current++;                 
              }
                steps+= "print()\n";
              
            }
            
            else {
                //check if the current element is equal to the last element in 
                //stack else break and print invalid
                int last = inst.pop();
                if(permut[i]==last){
                   steps+= "pop()"+"\n";
                }
                else{
                  steps="No";
                  break;
                }           
            }          
       }//end of for loop
 //======================================================================
//The answers were stored in a string called steps. 
     System.out.println(steps);
    
   }//end of else if input is valid.
   
   }
}
