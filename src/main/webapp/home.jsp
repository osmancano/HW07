<%@ page import="com.ironyard.data.Ticket" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TIY, HW06</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
            function validateForm1(){
                var results = [];
                var mySelect = document.getElementById("myForm1").getElementsByTagName("select");
                $('#myForm1').find('select').each(function (index, val) {
                    results[index] = $(val).val();
                });
                if(hasZero(results)){
                    alert("You Must choose all the 6 numbers");
                    return false;
                }
                if(hasDuplicates(results) || hasZero(results)){
                    alert("Numbers must be unique");
                    return false;
                }
            }
            function validateForm2() {
                var results = [];
                $('#myForm2').find('select').each(function (index, val) {
                    results[index] = $(val).val();
                });
                if(hasZero(results)){
                    alert("You Must choose all the 6 numbers");
                    return false;
                }
                if(hasDuplicates(results) || hasZero(results)){
                    alert("Numbers must be unique");
                    return false;
                }
            }
            function hasZero(array) {
                return array.indexOf("0") != -1;
            }
            function hasDuplicates(array) {
                return (new Set(array)).size !== array.length;
            }
    </script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar, .modal-footer, .modal-header {
            margin-bottom: 0;
            border-radius: 0;
            background-color: green;
        }
        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}
        /* Set black background color, white text and some padding */
        footer {
            background-color: green;
            color: white;
            padding: 15px;
        }
        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home.jsp"><img src="imgs/TIY.png" style="width:40px;"></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="home.jsp">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout">Welcome&nbsp;<span class="badge"><%=request.getSession().getAttribute("user")%></span>&nbsp;<span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-8 text-left">
            <h2>Simple Lottery Application</h2>
            <strong>Click <a href="/generateTicket" class="btn btn-sm btn-success">Generate Ticket</a> for quick pick</strong>
            <p><strong>or</strong></p>
            <div>
                <form action="/generateTicket" id="myForm1" method="post" onsubmit="return validateForm1()">
                    <strong>choose</strong>
                    <select name="num0" id="selectOne">
                        <option value="0">-</option>
                        <%
                            for(int i=1;i<54; i++){
                        %>
                        <option value="<%=i%>"><%=i%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="num1" id="selectTwo">
                        <option value="0">-</option>
                        <%
                            for(int i=1;i<54; i++){
                        %>
                        <option value="<%=i%>"><%=i%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="num2" id="selectThree">
                        <option value="0">-</option>
                        <%
                            for(int i=1;i<54; i++){
                        %>
                        <option value="<%=i%>"><%=i%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="num3" id="selectFour">
                        <option value="0">-</option>
                        <%
                            for(int i=1;i<54; i++){
                        %>
                        <option value="<%=i%>"><%=i%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="num4" id="selectFive">
                        <option value="0">-</option>
                        <%
                            for(int i=1;i<54; i++){
                        %>
                        <option value="<%=i%>"><%=i%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="num5" id="selectSix">
                        <option value="0">-</option>
                        <%
                            for(int i=1;i<54; i++){
                        %>
                        <option value="<%=i%>"><%=i%></option>
                        <%
                            }
                        %>
                    </select>
                    </select>
                    <button class="btn btn-sm btn-success">Pick</button> <strong>for self pick</strong>
                </form>
            </div>
            <h4>Here Are Your Tickets:</h4>
            <%
                Ticket winningTicket = (Ticket)request.getSession().getAttribute("winningTicket");
                if(winningTicket!=null){%>
                <div class="alert alert-info">
                    <strong>Winning ticket is set automatically click <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal">here</a> to set it manually</strong>
                </div>
            <%
                }
            %>
            <table class="table table-bordered">
                <tr>
                    <th>Ticket</th>
                    <th>No of Matches</th>
                    <th>Status</th>
                </tr>
                <c:forEach items="${ticket_list}" var="ticket">
                    <tr>
                        <td><strong><c:out value="${ticket}"/></strong></td>
                        <td><strong><c:out value="${ticket.noOfMatches}"/> </strong></td>
                        <td><strong>
                            <c:if test="${ticket.noOfMatches == 1 || ticket.noOfMatches == 2 }">
                             <span class="label label-info">Free ticket</span>
                            </c:if>
                            <c:if test="${ticket.noOfMatches == 3 }">
                                <span class="label label-info">3$ ticket</span>
                            </c:if>
                            <c:if test="${ticket.noOfMatches == 4 }">
                                <span class="label label-info">10$ ticket</span>
                            </c:if>
                            <c:if test="${ticket.noOfMatches == 5 }">
                                <span class="label label-info">50$ ticket</span>
                            </c:if>
                            <c:if test="${ticket.noOfMatches == 6 }">
                                <span class="label label-danger">1 Million $ ticket</span>
                            </c:if>
                        </strong></td>
                    </tr>
                </c:forEach>
            </table>
            </ul>
        </div>
        <div class="col-sm-4 text-left">
            <br/>

                <%
                    Integer noOfWinningTickets = (Integer) request.getSession().getAttribute("noOfWinningTickets");
                    if(winningTicket!=null){%>
            No of Winning tickets is:<span class="label label-danger"><%=noOfWinningTickets.intValue()%></span>
                <%
                    }
                %>
        </div>
    </div>
</div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Set The Winning Ticket</h4>
                </div>
                <div class="modal-body">
                    <strong>Click <a href="/generateWinningTicket" class="btn btn-sm btn-success">Generate</a> for random pick</strong>
                    <p><strong>or</strong></p>
                    <div>
                        <form action="/generateWinningTicket" method="post" id="myForm2" onsubmit="return validateForm2()">
                            <strong>choose</strong>
                            <select name="num0" id="selectOne">
                                <option value="0">-</option>
                                <%
                                    for(int i=1;i<54; i++){
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                    }
                                %>
                            </select>
                            <select name="num1" id="selectTwo">
                                <option value="0">-</option>
                                <%
                                    for(int i=1;i<54; i++){
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                    }
                                %>
                            </select>
                            <select name="num2" id="selectThree">
                                <option value="0">-</option>
                                <%
                                    for(int i=1;i<54; i++){
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                    }
                                %>
                            </select>
                            <select name="num3" id="selectFour">
                                <option value="0">-</option>
                                <%
                                    for(int i=1;i<54; i++){
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                    }
                                %>
                            </select>
                            <select name="num4" id="selectFive">
                                <option value="0">-</option>
                                <%
                                    for(int i=1;i<54; i++){
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                    }
                                %>
                            </select>
                            <select name="num5" id="selectSix">
                                <option value="0">-</option>
                                <%
                                    for(int i=1;i<54; i++){
                                %>
                                <option value="<%=i%>"><%=i%></option>
                                <%
                                    }
                                %>
                            </select>
                            </select>
                            <button class="btn btn-sm btn-success">Pick</button> <strong>for manual pick</strong>
                        </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>


</div>
<footer class="container-fluid text-center">
    <p>TIY, all Rights Reserved</p>
</footer>

</body>
</html>