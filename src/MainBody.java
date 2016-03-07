import java.util.Scanner;

public class MainBody {
	private static int[][] arr = new int[6][7];
	
	public static void printArr(int[][] ar){//tested
		//prints the array
		int i,j;
		for(i=0;i<ar.length;i++){
			for(j=0;j<ar[0].length;j++){
				System.out.print(ar[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void initArr(int[][] ar){//tested
		//initializing all the elements in the array to 3.
		int i,j;
		for(i=0;i<ar.length;i++){
			for(j=0;j<ar[0].length;j++){
				ar[i][j] = 3;
			}
		}
	}
	public static void changeArr(int x,int col,int[][] ar){//tested
		boolean colFlag = false;
		int i;
		// if the column is full return
		if(ar[0][col]!=3)
			return;
		//making sure that the column is not completely empty.
		for(i=0;i<6;i++){
			if(ar[i][col] != 3)
				colFlag =true;
		}
		//if the column is not empty 
		if(colFlag){
			for(i=0;i<6;i++){
				if(ar[i][col]!= 3){
					ar[i-1][col] = x;
					break;
				}
			}
		}//if the column is empty then set the last index to the value.
		else{
			ar[5][col] = x;
		}
		
	}
	
	public static void checkWinner(int x,int col,int[][] ar){
		//TODO: check if player 'x' won from the last change of the array 'ar' in column 'col'
	}
	
	public static void main(String[] args){
		initArr(arr);
		Scanner scan = new Scanner(System.in);
		boolean x = true;
		int turn = 1,winner=0,col;
		
		int y = scan.nextInt();
		
		while(x){
			if(turn ==1)
				turn =2;
			else
				turn = 1;
			//TODO: handle if the array is completely full.
			switch(turn){
				case 1:
					System.out.println("player 1 play in column = ");
					col = scan.nextInt();
					// TODO: handle wrong inputs.
					changeArr(1,col,arr);
					printArr(arr);
					if(checkWinner(1,col,arr)){
						x = false;
						break;
					}
					break;
				case 2:
					System.out.println("player 2 play in column = ");
					col = scan.nextInt();
					// TODO: handle wrong inputs.
					changeArr(2,col,arr);
					printArr(arr);
					if(checkWinner(2,col,arr)){
						x = false;
						break;
					}
					break;
				default:
					break;
			}
			
		}
	}
}
