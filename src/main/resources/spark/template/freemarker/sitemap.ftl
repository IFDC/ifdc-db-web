<!DOCTYPE html>
<html>
    <head>
        <#include "header.ftl">
    </head>

    <body>

        <#include "nav.ftl">

        <h3>Site Map</h3>
        <div class="container-fluid bg-2">
            <div class="container">
                <br/>

                <div class="links">
                    <!--      <ul>
                              <li><a href="/upload">UPLOAD PAGE</a></li>
                          </ul>-->
                    <ul>
                        <p>Project :</p>
                        <li><a href="/project/create">Create New Project</a></li>
                        <li><a href="/project/list">View All Projects</a></li>
                        <!--<li><a href="/project/search">Search Existing Project</a></li>-->
                    </ul>
                    <ul>
                        <p>Activity :</p>
                        <li><a href="/activity/create">Create New Activity</a></li>
                        <li><a href="/activity/list">View All Activities</a></li>
                    </ul>
                    <ul>
                        <p>Report :</p>
                        <li><a href="/activity/list">Edit Report From List</a></li>
                        <li><a href="/report/list">View All Reports</a></li>
                    </ul>
                    <ul>
                        <p>Indicator :</p>
                        <li><a href="/indicator/create">Create New Indicator</a></li>
                        <li><a href="/indicator/list">View All Indicators</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <#include "footer.ftl">

    </body>
</html>
