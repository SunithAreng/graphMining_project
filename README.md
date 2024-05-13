# Instructions for running this program in Eclipse

### 1. Cloning to Eclipse
Go to the GitHub repository https://github.com/MSCI240-f2021/final-project-2021f-SunithAreng.  
Click on the green coloured box "code". Then **Code > Clone > SSH ** and copy the SSH code.
Make sure access has been obtained for the GitHub repository. Go to Eclipse, go to git view and select clone "git repository" option. The SSH code should be already pasted there and if not then manually paste the SSH code in the URI box. The rest should be auto-filled and then hit next and finish.

### 2. Importing as local Java Project
Once the repository has been successfully cloned to Eclipse, right click on the project name **final-project-2021f-SunithAreng [main] **, select **Import Projects...** and click finish. This will create a import the repository as a local java project which can be found in the Package Explorer.

### 3. Convert to Maven Project
In this step, check if the project shows any red box beside the name of the project in package explorer in Eclipse. This is due to the Maven nature of the project, if there is a red box then right click on the project name **final-project-2021f-SunithAreng** and select  ** Configure > Convert to Maven Project** and hit finish. If the java project is already a Maven project then this step can be ignored.

### 4. Create build.xml file
Next, check if the project has a build.xml file. If such file exists then this step can be ignored. If no such file exists then right click on the project name **final-project-2021f-SunithAreng** and select ** Export > General > Ant Buildfiles > Next > Finish **. This will create a build file that enable all the java programs to run.


### 5. Running the program
Go to the project name **final-project-2021f-SunithAreng**. Click on the ** Code > (default package) > GraphMain ** and run using the green play button. The output will  be shown in the console. This program runs the main method, object IntGraphList class and uses the input.txt file. 

### 6. Running the test cases
Go to the project name **final-project-2021f-SunithAreng**. Click on the ** test > (default package) > TestIntGraphList ** and run using the green play button. This should automatically open the JUnit tab at the left side replacing the Package Explorer tab. All the 35 tests should pass. 
