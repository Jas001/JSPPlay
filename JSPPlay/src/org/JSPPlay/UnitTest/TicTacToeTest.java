package org.JSPPlay.UnitTest;

//import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.JSPPlay.Test.*;

//import com.sun.jmx.trace.Trace;
public class TicTacToeTest {

	private TicTacToe target;
    @Before public void initialize() {
       
    target = new TicTacToe(); 
    }


	
	@Test
	public void IsGameOvertest() {
		char ltr = 'x';
		//horizontal	
		Assert.assertEquals(true, target.isGameOverByLetter("xxxeeeeee",ltr));//"top horizontal failed"
		Assert.assertEquals(true, target.isGameOverByLetter("eeexxxeee",ltr));//"middle horizontal failed"
		Assert.assertEquals(true, target.isGameOverByLetter("eeeeeexxx",ltr));//"bottom horizontal failed"
		//vertical
		Assert.assertEquals(true, target.isGameOverByLetter("xeexeexee",ltr));//"bottom horizontal failed"
		Assert.assertEquals(true, target.isGameOverByLetter("exeexeexe",ltr));//"bottom horizontal failed"
		//diagnals
		Assert.assertEquals(true, target.isGameOverByLetter("xeeexeeex",ltr));//"bottom horizontal failed"
		Assert.assertEquals(true, target.isGameOverByLetter("eexexexee",ltr));//"bottom horizontal failed"
		//try a few falses.
		Assert.assertEquals(false,target.isGameOverByLetter("eeeeeeeee",ltr));// validate default false.
		Assert.assertEquals(false,target.isGameOverByLetter("oooeeeeee",ltr));// validate letter works correctly.
		Assert.assertEquals(false,target.isGameOverByLetter("xxeeeeeex",ltr));// validate letter works correctly.
	}
	
	@Test
	public void GetMoveCounttest(){
		Assert.assertEquals(3,target.getMovecount("oooeeeeee",'o'));//check for 3.
		Assert.assertEquals(0,target.getMovecount("eeeeeeeee",'o'));//check for 0.
		Assert.assertEquals(1,target.getMovecount("eeeeeeeeo",'o'));//check for 1.
	}
	
	@Test
	public void CheckHorizontalTest()
	{
		//row 0
		Assert.assertEquals(false,target.checkWinHorizontal("oeeeeeeee",'o'));
		Assert.assertEquals(false,target.checkWinHorizontal("eoeeeeeee",'o'));
		Assert.assertEquals(false,target.checkWinHorizontal("eeoeeeeee",'o'));
		Assert.assertEquals(true,target.checkWinHorizontal("ooeeeeeee",'o'));
		Assert.assertEquals(true,target.checkWinHorizontal("eooeeeeee",'o'));
		//row1
		Assert.assertEquals(false,target.checkWinHorizontal("eeeoeeeee",'o'));
		Assert.assertEquals(false,target.checkWinHorizontal("eeeeoeeee",'o'));
		Assert.assertEquals(false,target.checkWinHorizontal("eeeeeoeee",'o'));
		Assert.assertEquals(true,target.checkWinHorizontal("eeeooeeee",'o'));
		Assert.assertEquals(true,target.checkWinHorizontal("eeeeooeee",'o'));
		//row2
		Assert.assertEquals(false,target.checkWinHorizontal("eeeeeeoee",'o'));
		Assert.assertEquals(false,target.checkWinHorizontal("eeeeeeeoe",'o'));
		Assert.assertEquals(false,target.checkWinHorizontal("eeeeeeeeo",'o'));
		Assert.assertEquals(true,target.checkWinHorizontal("eeeeeeooe",'o'));
		Assert.assertEquals(true,target.checkWinHorizontal("eeeeeeeoo",'o'));
		//check fail no move.
		Assert.assertEquals(false,target.checkWinHorizontal("eeeeeexoo",'o'));
	}
	
	@Test
	public void CheckVerticalTest()
	{
		Assert.assertEquals(false,target.checkWinVertical("oeeeeeeee",'o'));
		Assert.assertEquals(true,target.checkWinVertical("oeeoeeeee",'o'));
		Assert.assertEquals(6, target.nextindex);
		Assert.assertEquals(true,target.checkWinVertical("oeeeeeoee",'o'));
		Assert.assertEquals(3, target.nextindex);
		Assert.assertEquals(false,target.checkWinVertical("oeexeeoee",'o'));
		
		Assert.assertEquals(false,target.checkWinVertical("eoeeeeeee",'o'));
		Assert.assertEquals(true,target.checkWinVertical("eoeeoeeee",'o'));
		Assert.assertEquals(7, target.nextindex);
		Assert.assertEquals(true,target.checkWinVertical("eoeeeeeoe",'o'));
		Assert.assertEquals(4, target.nextindex);
		Assert.assertEquals(false,target.checkWinVertical("eoeexeeoe",'o'));

		Assert.assertEquals(false,target.checkWinVertical("eeoeeeeee",'o'));
		Assert.assertEquals(true,target.checkWinVertical("eeoeeoeee",'o'));
		Assert.assertEquals(8, target.nextindex);
		Assert.assertEquals(true,target.checkWinVertical("eeoeeeeeo",'o'));
		Assert.assertEquals(5, target.nextindex);
		Assert.assertEquals(false,target.checkWinVertical("eeoeexeeo",'o'));		
	}
	@Test
	public void CheckDiagTest()
	{
		//target.Last="00";// front slash \
		Assert.assertEquals(false,target.checkWinDiagonal("oeeeeeeee",'o'));
		Assert.assertEquals(true,target.checkWinDiagonal("oeeeeeeeo",'o'));
		Assert.assertEquals(4,target.nextindex);
		Assert.assertEquals(true,target.checkWinDiagonal("oeeeoeeee",'o'));
		Assert.assertEquals(8,target.nextindex);
		//target.Last ="22";//front slash 3rd position.
		Assert.assertEquals(true,target.checkWinDiagonal("eeeeoeeeo",'o'));
		Assert.assertEquals(0,target.nextindex);
		Assert.assertEquals(true,target.checkWinDiagonal("oeeeeeeeo",'o'));
		Assert.assertEquals(4,target.nextindex);
		
		//target.Last = "11"; //middle position.
		Assert.assertEquals(false,target.checkWinDiagonal("eeeoeeeee",'o'));
		Assert.assertEquals(true,target.checkWinDiagonal("eeeeoeeeo",'o'));
		Assert.assertEquals(0,target.nextindex);
		Assert.assertEquals(true,target.checkWinDiagonal("eeeeoeoee",'o'));
		Assert.assertEquals(2,target.nextindex);
		Assert.assertEquals(true,target.checkWinDiagonal("eeoeoeeee",'o'));
		Assert.assertEquals(6,target.nextindex);
		Assert.assertEquals(true,target.checkWinDiagonal("oeeeoeeee",'o'));
		Assert.assertEquals(8,target.nextindex);
	
		//target.Last="20"; //back slash /
		Assert.assertEquals(false,target.checkWinDiagonal("eeoeeeeee",'o'));
		Assert.assertEquals(true,target.checkWinDiagonal("eeoeeeoee",'o'));
		Assert.assertEquals(4,target.nextindex);
		Assert.assertEquals(true,target.checkWinDiagonal("eeoeoeeee",'o'));
		Assert.assertEquals(6,target.nextindex);
		
		//target.Last ="02";//front slash 3rd position.
		Assert.assertEquals(false,target.checkWinDiagonal("eeeeeeoee",'o'));
		Assert.assertEquals(true,target.checkWinDiagonal("eeeeoeoee",'o'));
		Assert.assertEquals(2,target.nextindex);
		Assert.assertEquals(true,target.checkWinDiagonal("eeoeeeoee",'o'));
		Assert.assertEquals(4,target.nextindex);
		
		//try with an invalid character in there.
		Assert.assertEquals(false,target.checkWinDiagonal("eeoexeoee",'o'));	
		
	}
	@Test
	public void DoMove()
	{

		String newboard = target.doMove("oxoexexee");
		//oxo
		//exe
		//xee
		Assert.assertEquals("oxoexexoe", newboard);
	}


}
