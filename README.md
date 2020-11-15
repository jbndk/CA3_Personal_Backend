[![Build Status](https://travis-ci.com/jbndk/CA3_Personal_Backend.svg?branch=master)](https://travis-ci.com/jbndk/CA3_Personal_Backend)

Instructions for a quick start project

Link to frontend: https://github.com/jbndk/CA3-Frontend
</br>

Backend

- Clone the project from GitHub
- Delete the folder named ".git"
- Make your own ".git" by typing "git init" in Git Bash
- Create a new repository on GitHub
- Rename the project in Netbeans and check that the war-file has the name that you would like the project to have
  after your domain.
- Change the URL in pom.xml to: https://YOURDOMAIN.dk/manager/text

- Set up two local databases in MySql ("DATABASE" and "DATABASE_TEST")
- In the file, "persistence.xml", change the name of the database and the test database to the ones you just
  created in MySql
- In Netbeans, run the file "SetupTestUsers.java", and check that the database now contains users and roles
- Migrate the local database to the droplet database using the Migration Wizard in MySql

- Push the project to your new GitHub repository
- Log in to Travis-ci.com and change the environment variables
- Trigger build and see that it passes

- After making changes to the project, in Git Bash, type "mvn clean test" before pushing
- Push the project to GitHub
- Check that Travis has deployed it to your droplet.

Frontend

- Clone the GitHub repo
- Change the URL in the settings.js folder
- In Git Bash, type "npm install"
- Type "npm start" to see the project in your browser
