# Repair Agency (Servlet)

### Task description
> User can create a request for product repair.
> Manager can accept the request indicating the price, or
>reject the request, indicating the reason. Master can fulfill the Manager's
> accepted request. User can leave feedback for completed works


### Installation & Сonfiguring 
1. Clone repository or download project from gitHub.
2. Connect Apache Tomcat to the project. For successful project launching you should have installed Apache Tomcat (Recommended Tomcat 9). 
You can get it [here](https://tomcat.apache.org/download-90.cgi).
To connect Tomcat to the Repair Agency via IDE (IntelliJ IDEA)  select the  "Add configuration" setting and select "Tomcat server" > "Local" menu items. Then select corresponding Tomcat version. Also don't forget to  set artifact -  *repair-agency-servlets: war exploded*
3.  Connect DB to the project.

    To connect your database to the project you need to specify following credentials of your database in the *context.xml* file.
    ```sh
    username="yourDbUsername"
    password="yourDbPassword"
    driverClassName="com.db.driver"
    url="jdbc:db://url"
    validationQuery="your_validation_query"
    ```


### Launching
##### Via IDE
Open project via IDE (Preferred to use IntelliJ IDEA) and run  project in the appropriate way for your IDE.
