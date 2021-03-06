<%-- 
    Document   : priceBundle
    Created on : March 17, 2018, 12:06:11 AM
    Author     : Jacquelyn
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.PurchaseHistory"%>
<%@page import="DAO.PurchaseHistoryDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.Inventory"%>
<%@page import="DAO.InventoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//cdn.muicss.com/mui-0.9.36/css/mui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<%@include file="protect.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Price Bundling</title>
        <script src="js/jquery-1.12.3.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

    </head>
    <body>
        <%             String usernameAcc = (String) session.getAttribute("user");

             if (usernameAcc.equals("qingyang") || usernameAcc.equals("cynthia")) {%>
        <%@include file="sideNavBar.jsp" %>
        <%} else {%>
        <%@include file="nonAdminSideNavBar.jsp" %>
        <% }


        %>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#myTable').dataTable({
                    "bPaginate": false,
                    "bLengthChange": true,
                    "bFilter": false,
                    "bInfo": false,
                    "bAutoWidth": false,
                    "bSorted": false,
                    "order": [],
                    "ordering": false
                });
            });

            $(document).ready(function () {
                $('#topTable').dataTable({
                    "bPaginate": false,
                    "bLengthChange": true,
                    "bFilter": false,
                    "bInfo": false,
                    "bAutoWidth": false,
                    "bSorted": false,
                    "order": [],
                    "ordering": false
                });
            });
        </script>
        <form class="mui-form--inline" action="PriceBundlingController" method="post">
            <div class="subPageContent">
                <div class ="mui-panel "> 
                    <h1>Bundle Pricing Recommendation</h1>
                    <div class="mui-divider"></div>
                    <table>
                        <tbody>
                            <tr>
                                <td><span class="priceBundleSpan">Filter by month/year</span><span class="priceBundleSpan" style="float:right">:</span> </td>
                                <td><div class="mui-select">
                                        <select name="month" required>
                                            <option value=''>Please select</option>
                                            <option value='1'>January</option>
                                            <option value='2' >February</option>
                                            <option value='3' >March</option>
                                            <option value='4' >April</option>
                                            <option value='5' >May</option>
                                            <option value='6' >June</option>
                                            <option value='7' >July</option>
                                            <option value='8' >August</option>
                                            <option value='9' >September</option>
                                            <option value='10' >October</option>
                                            <option value='11' >November</option>
                                            <option value='12' >December</option>
                                        </select>
                                    </div></td>
                                <td><div class="mui-select">
                                        <select name="year" required>
                                            <option value="" >Please select</option>
                                            <option value="2017" >2017</option>
                                            <option value="2018" >2018</option>
                                        </select>
                                    </div></td>
                            </tr>
                            <tr>
                                <td><span class="priceBundleSpan">Number of result to display:</span></td>
                                <td> <div class="mui-select">
                                        <select name="numResult" required>
                                            <option value='' selected>Please select</option>
                                            <option value='5' >5</option>
                                            <option value='10' >10</option>
                                            <option value='15' >15</option>
                                        </select>
                                    </div> </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td><button type="submit" name="submit" class="mui-btn mui-btn--raised mui-btn--primary" style="font-size:18px;float:right" > Submit <i class="fa fa-caret-right" style="font-size:18px;"></i></button></td>
                            </tr>
                        </tbody>
                    </table>

                    <%  HashMap<Integer, Integer> topXStockList = (HashMap<Integer, Integer>) request.getAttribute("topXStock");

                        

                        PurchaseHistoryDAO purchaseDao = new PurchaseHistoryDAO();
                        InventoryDAO inventoryDAO = new InventoryDAO();
                        String inventoryName = "";
                        int quantity = 0;

                        HashMap<Integer, Integer> lowXStockList = (HashMap<Integer, Integer>) request.getAttribute("lowXStock");
                        purchaseDao = new PurchaseHistoryDAO();
                        inventoryDAO = new InventoryDAO();

                        String text = (String) request.getAttribute("word");
                        if (text != null) {

                            out.println("You have selected: <b><u>" + text + "</b></u><br /><br />");
                        }

                        if (topXStockList != null && !topXStockList.isEmpty() && lowXStockList != null) {
                            int numResult = (Integer) request.getAttribute("numResult");
                    %>
                </div>
                <div class ="leastPriceBundle">
                    <div class ="mui-panel">
                        <h3> Least <u><b><%=numResult%></b></u> Selling Sock</h3>
                        <br />
                        <table id="myTable">
                            <thead>
                                <tr> 
                                    <th id="aquantity"> Product Name </th>
                                    <th> Quantity </th>
                                </tr>
                            </thead>
                            <%for (int keyId : topXStockList.keySet()) {
                                    Inventory i = inventoryDAO.retrieve(keyId);
                                    inventoryName = i.getName();
                                    System.out.println("top name is" + inventoryName);
                                    //    inventoryName = keyId;
                                    quantity = topXStockList.get(keyId);

                            %>
                            <tbody>
                                <tr>    
                                    <td id="iName"><%=inventoryName%></td>                  
                                    <td width="60px" id="quantity"><%=quantity%></td>
                                </tr>
                                <%
                                    }

                                %>

                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="topPriceBundle">
                    <div class ="mui-panel">
                        <h3> Top <u><b><%=numResult%></b></u> Selling Sock</h3>   
                        <br />
                        <table id="topTable">
                            <thead>

                                <tr>
                                    <th id="aquantity"> Product Name </th>
                                    <th> Quantity </th>
                                </tr>
                            </thead>
                            <%for (int keyId : lowXStockList.keySet()) {
                                    Inventory i = inventoryDAO.retrieve(keyId);
                                    inventoryName = i.getName();
                                    quantity = lowXStockList.get(keyId);
                                    System.out.println("name is" + inventoryName);
                                    System.out.println(quantity);
                            %>

                            <tbody>
                                <tr>
                                    <td id="iName"><%=inventoryName%></td>
                                    <td id="quantity"><%=quantity%></td>
                                </tr>
                                <%
                                    }
                                %>    
                            </tbody>
                        </table>
                    </div>
                </div>
                <%
                    } else {
                        String error = (String) request.getAttribute("errorMsg");
                        if (error == null) {
                            error = "";
                        } else {
                            out.println("<p style='color:red'>" + error + "</p>");
                        }
                    }

                %>

            </div>
        </form> 
    </body>
</html>
