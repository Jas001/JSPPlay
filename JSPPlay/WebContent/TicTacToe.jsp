<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="org.JSPPlay.Test.TicTacToe"
    %>
<%!
String data = "";
String msg = "";
String useraction = "";
boolean GameOver;
public boolean CheckGameEndings(HttpServletRequest req,String data)
{
	TicTacToe ttt = new TicTacToe();
	//check for game over by each player.
	if(ttt.isGameOverByLetter(data,'x'))
	{
		msg="Game Over you win!";
		
		//set the score.
		Integer playerscore = (Integer)req.getSession().getAttribute("playerscore");
		req.getSession().setAttribute("playerscore",playerscore + 1);
	
		GameOver = true;
	}
	else if(ttt.isGameOverByLetter(data, 'o'))
	{
		msg="Game Over I win!";
		//set the score.
		Integer compscore = (Integer)req.getSession().getAttribute("computerscore");
		req.getSession().setAttribute("computerscore",compscore + 1);
		
		
		GameOver = true;
	}
	else if(data.contains("e") == false)
	{
		msg="Scratch";
		GameOver = true;
	}
	return GameOver;
}

public void Load(HttpServletRequest req)
{
	//create our TickTackToe Class.
	TicTacToe ttt = new TicTacToe();
	
	//if we don't have session variables set the score.
	if(req.getSession().getAttribute("computerscore") == null)
	{
		req.getSession().setAttribute("computerscore",0);
		req.getSession().setAttribute("playerscore",0);
	}
	
	//did we get any data?
	data = req.getParameter("boarddata");
	
	if(data == null)
	{
		//reset data.
		data="eeeeeeeee";
		msg = "You go first.";
		GameOver = false;
	}else{
		//first apply users action
		useraction = req.getParameter("useraction");
		if(useraction != null)
		{
			data = ttt.SetAction(useraction,data);
		}

		//check for game over by each player.
		if(!this.CheckGameEndings(req, data))
		{
			//take a move
			data = ttt.doMove(data);
			
			//do another check for a solution.
			if(!this.CheckGameEndings(req, data))
				msg="Your Turn.";
		}
	}
}
%>


<%
//Call the load function to run.
Load(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TicTacToe</title>
</head>
<body>
<script language="javascript">
function setit(str){
	document.getElementById("useraction").value = str;
	//now submit the page.
	
	document.forms["frm"].submit();
}
</script>

<h1>TicTacToe</h1>
<form action="TicTacToe.jsp" method="get" name="frm">
<input type="hidden" name="boarddata" value="<%=data%>">
<input type="hidden" name="useraction" id="useraction">
<table width="100">
<tr><td><div class="x">Player:<%=session.getAttribute("playerscore")%></div></td><td><div class="o">Computer:<%=session.getAttribute("computerscore")%></div></td></tr>
</table>

<div class="msg"><%=msg %></div>
<table>
<%
//print the board.
	for(int y = 0; y < 3; y++)
	{
		%><tr><%
		for(int x = 0; x < 3; x++)
		{
			%><td><%
			//get our data.
			char c = data.charAt((y * 3) + x);
			switch(c){
				case 'e':
					if(GameOver == true){
					 %><div class="e">-</div><% 
					}else{
					 %>
					<input type="button" name="<%=x%><%=y%>" value="    " onclick="setit('<%=x%><%=y%>');"><%
					}				
					break;
				case 'x':
					%><div class="x">X</div><%
				break;
				case 'o':
					%><div class="o">O</div><%
				break;
			}
			%></td><%
		}
		%></tr><%	
	}
%>
</table>
<%if(GameOver){ %>
<a href="TicTacToe.jsp">Play Again!</a>
<%} %>
</form>
</body>
</html>