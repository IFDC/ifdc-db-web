<!DOCTYPE html>
<html>
    <head>
        <#include "../header.ftl">
    </head>
    <style>
        td {
            padding: 5px;
            text-align: left;
        }
        table#t01 {
            width: 30%;
        }
        table#t02, table#t02 th, table#t02 td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        table#t02 th, table#t02 td {
            padding: 5px;
            text-align: center;
        }
        table#t02 {
            width: 30%;
        }
        table#t02 tr:nth-child(even) {
            background-color: #eee;
        }
        table#t02 tr:nth-child(odd) {
            background-color:#fff;
        }
        table#t02 th {
            background-color: lightskyblue;
            color: black;
        }
    </style>
    <script>
        function addNewRow() {
        var x = document.getElementById("t02");
            var r = x.insertRow();
            var year = r.insertCell();
            year.innerHTML = "<input type='number' name='year' min='1960' max='${curYear!9999}' required>"
            <#list indicators as indicator >
            r.insertCell().innerHTML = "<input type='text' name='${indicator["name"]!}'>";
            </#list>
            var newColNum = document.getElementById('t02').rows[0].cells.length - ${indicators?size} - 1
            for (i = 0; i < newColNum; i++) {
                r.insertCell().innerHTML = "<input type='text' name='indi_value'>";
            }
        }

        function addNewCol() {
            var myform = $('#t02')
            myform.find('tr').each(function(){
                var trow = $(this);
                if (trow.index() === 0){
                    trow.append("<th><select name='indi_name' required><#list allIndicators as indicator ><option value = '${indicator["name"]}' >${indicator["title"]!} <#if indicator["unit"]??>(${indicator["unit"]})</#if></#list></select></th>");
                } else{
                    trow.append("<td><input type='text' name='indi_value'></td>");
                }
            });
        }
    </script>
    <body>

        <#include "../nav.ftl">

        <div class="container">

            <fieldset>
                <legend>Activity Info</legend>
                <#if operation_result == "Failed" >
                <p>Report Update Got Issue</p>
                <#else>
                <form id="editReportForm" method="post">
                    <div>
                        <table id="t01">
                            <tr>
                                <td><label>Project : </label></td>
                                <td><input type="hidden" name="project_name" value="${project_name!}" required>${project_name!}</td>
                            </tr>
                            <tr>
                                <td><label>Crop : </label></td>
                                <td><input type="hidden" name="crop" value="${crop!}" required>${crop!}</td>
                            </tr>
                            <tr>
                                <td><label>Technology : </label></td>
                                <td><input type="hidden" name="tech" value="${tech!}" required>${tech!}</td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                    <div>
                        <fieldset>
                            <legend>M&E Report</legend>
                            <input type="button" value="Add Indicator" onclick="addNewCol()">
                            <input type="button" value="Add Year" onclick="addNewRow()">
                            <table id="t02">
                                <caption>Activity Indicators</caption>
                                <tr>
                                    <th>Year</th>
                                    <#list indicators as indicator>
                                    <th>${indicator["title"]} <#if indicator["unit"]??>(${indicator["unit"]})</#if></th>
                                    </#list>
                                </tr>
                                <#list years as year>
                                <tr>
                                    <td><input type="hidden" name="year" value="${year!}" required>${year}</td>
                                    <#list indicators as indicator>
                                    <td><input type="text" name="${indicator["name"]!}" value="${indicatorValues[year][indicator["name"]]!}"></td>
                                    </#list>
                                </tr>
                                </#list>
                            </table>
                            <br/>
                            <input type="submit" value="save">
                        </fieldset>
                    </div>
                    <br/>
                </form>
                </#if>
            </fieldset>

        </div>

        <#include "../footer.ftl">
    </body>
</html>
