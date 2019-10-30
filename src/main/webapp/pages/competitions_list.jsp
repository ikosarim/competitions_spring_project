<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<html>
    <head>
        <title>Competitions List</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body class="w3-light-grey">
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Super competitions!</h1>
        </div>

        <div class="w3-container w3-center w3-margin-bottom w3-padding">
            <div class="w3-card-4">
                <div class="w3-container w3-light-blue">
                    <h2>Competitions</h2>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Competition Name</th>
                            <th>Competition Description</th>
                            <th>Competition Reward</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${competitions}" var="competition">
                            <tr>
                                <td>${competition.competitionName}</td>
                                <td>${competition.competitionDescription}</td>
                                <td>${competition.competitionReward}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>