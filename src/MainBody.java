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

	/* check if the row changed has 4 in a row
	 * check if the column changed has 4 in a row
	 * check for diagonal of it has 4 in a row
     */
	public static boolean checkForFourInCol(int playerNum,int col,int[][] ar,int start)
	{
		int strike = 0, count = 0;
		for (int i = start; i < 6 && count < 4; i++)
		{
			if (ar[i][col] == playerNum)
			{
				strike = 1;
				count++;
			}
			else
			{
				count = 0;
				strike = 0;
			}

		}
		return (count == 4 && strike == 1);
	}

	public static boolean checkForFourInRow(int playerNum, int row, int[][] ar)
	{
		int strike = 0, count = 0;
		for (int i = 0; i < 7 && count < 4; i++)
		{
			if (ar[row][i] == playerNum)
			{
				count++;	strike = 1;
			}
			else
			{
				count = 0; 	strike = 0;
			}
		}
		return (count == 4 && strike == 1);
	}

	public static boolean checkForFourDiagonalHelper(int playerNum, int xCoordinate, int yCoordinate, int move, int[][] ar)
	{
        int strike = 0, count = 0;
        // Draw the metrix and walk with it you will get it
        // x alway --
        // y depend on the direction of going (move)
        for (int tempX = xCoordinate, tempY = yCoordinate; tempX > 0 && tempY >= 0 && tempY < 7 && count < 4; tempX--, tempY += move)
        {
            if (ar[tempX][tempY] == playerNum)
            {
                System.out.println(ar[tempX][tempY]+" "+move);
                count++;    strike = 1;
            }
            else
            {
                count = 0;  strike = 0;
            }
        }
        System.out.println(strike+count);
        return (count == 4 && strike == 1);
	}

    // Checks for four in diagonal in both directions
	public static boolean checkForFourDiagonal(int playerNum,int peakX, int peakY, int[][] ar)
	{
        // rightEndX and RightEndY are the co-oridnates of the end of the diagonal down right
        int rightEndX = peakX, rightEndY = peakY;

        // leftEndX and leftEndY are the co-oridnates of the end of the diagonal down left
        int leftEndX = peakX, leftEndY = peakY;

        // check whether the coin at the right or the down corner or not
        // if not get the down-right corner
        while (rightEndX < 5 && rightEndY < 6)
        {
            rightEndX++;
            rightEndY++;
        }

        // using the helper and passing -1 to decrease the y by 1
        // the next point in the diagonal
        if (checkForFourDiagonalHelper(playerNum,rightEndX, rightEndY,-1, ar))
            return true;


        // checking whether the coint at the left or down corner
        while (leftEndX < 5 && leftEndY > 0)
        {
            leftEndX++;     leftEndY--;
        }

        // using the helper and passing 1 to increase y by 1
        // the next point in the diagonal
        if (checkForFourDiagonalHelper(playerNum, leftEndX, leftEndY, 1, ar))
            return true;

        return false;
	}

	public static boolean checkWinner(int x,int col,int[][] ar)
	{
		//TODO: check if player 'x' won from the last change of the array 'ar' in column 'col'
		int start = 0;
		for (int i = 0; i <= 5; i++)
		{
			if (arr[i][col] == x)
			{
				start = i;
				break;
			}
		}
		if (checkForFourInCol(x,col,ar,start))
			return true;
		if (checkForFourInRow(x,start,ar))
			return true;
        if (checkForFourDiagonal(x,start,col,ar))
            return true;
        return false;
	}

	public static boolean fullMatrix()
	{
        for (int i = 0; i < 7; i++)
        {
            if (arr[0][i] == 3)
                return false;
        }

        return true;
	}

	public static boolean fullCol(int colNum)
	{
        return (arr[0][colNum] != 3);
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
			//handle if the array is completely full.
			if (fullMatrix())
                break;

			switch(turn){
				case 1:
					System.out.println("player 1 play in column = ");
					col = scan.nextInt();
					// TODO: handle wrong inputs.
					// use fullCol
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
