<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand navbar-static-top"><img src="/IFDC-S_1.png" height="100%" alt="IFDC"></a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active">
                <a href="/"><span class="glyphicon glyphicon-home"></span> Home</a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-folder-open"></span> Project <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/project/create"><span class="glyphicon glyphicon-file"></span> Create New</a></li>
                    <li><a href="/project/list"><span class="glyphicon glyphicon-list-alt"></span> View List</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-grain"></span> Activity <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/activity/create"><span class="glyphicon glyphicon-file"></span> Create New</a></li>
                    <li><a href="/activity/list"><span class="glyphicon glyphicon-list-alt"></span> View List</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-tasks"></span> M&E Report <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/activity/list"><span class="glyphicon glyphicon-file"></span> Edit From List</a></li>
                    <li><a href="/report/list"><span class="glyphicon glyphicon-list-alt"></span> View All</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-filter"></span> Indicator <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="/indicator/create"><span class="glyphicon glyphicon-file"></span> Create New</a></li>
                    <li><a href="/indicator/list"><span class="glyphicon glyphicon-list-alt"></span> View List</a></li>
                </ul>
            </li>
            <!--            <li>
                            <a href="https://devcenter.heroku.com/articles/how-heroku-works"><span class="glyphicon glyphicon-user"></span> How Heroku Works</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-info-sign"></span> Getting Started Guides <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-ruby">Getting Started with Ruby on Heroku</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-nodejs">Getting Started with Node on Heroku</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-php">Getting Started with PHP on Heroku</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-python">Getting Started with Python on Heroku</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-java">Getting Started with Java on Heroku</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-go">Getting Started with Go on Heroku</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-clojure">Getting Started with Clojure on Heroku</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-scala">Getting Started with Scala on Heroku</a></li>
                                <li class="divider"></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-heroku-and-connect-without-local-dev">Getting Started on Heroku with Heroku Connect</a></li>
                                <li><a href="https://devcenter.heroku.com/articles/getting-started-with-jruby">Getting Started with Ruby on Heroku (Microsoft Windows)</a></li>
                            </ul>
                        </li>-->
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <#if currentUser?? >
            <li class="active navbar-left">
                <a>Hello, ${currentUser}</a>
            </li>
            <li class="navbar-defalut">
                <a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </li>
            <#else>
            <li class="navbar-left">
                <a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a>
            </li>
            <li class="navbar-defalut">
                <a href="/register"><span class="glyphicon glyphicon-user"></span> Register&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </li>
            </#if>
        </ul>
    </div><!-- /container -->
</nav><!-- /navbar wrapper -->