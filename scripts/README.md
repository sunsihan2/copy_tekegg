# TekkEgg Developer Module
This module contains several automations to ease development. 

### Install Instructions
To install the module in your user profile follow the instructions below  
1. Run `$profile` command in powershell
2. navigate to the provided filepath
3. Create a new folder called `Modules`
4. Inside the `Modules` folder create a new folder called `TekEggDeveloperScript`
5. Lastly copy `TekEggDeveloperScript.psm1` into `TekEggDeveloperScript` folder

### Commands
Below is a list of the commands with the alias and description.

### `Deploy-GitCommit`
#### Alias: `dpgc`
This script will automate staging all files, commiting them with user inputed commit message, and pushing to current branch.

### `Merge-GitDevelop`
#### Alias: `mgdv`
This script will automate the proccess of pulling develop into your current branch to check for merge conflicts. Then it will checkout to develop and merge your current branch.

### `Install-DeveloperApplicatons`
#### Alias: `isdvapp`
The New Team Member scripts installs the following applications

* Chocolatey
* PoshGit
* Git
* Open JDK 17
* NodeJS
* MySQL
* Python 3
* Gradle
* Intellij Community
* Visual Studio Code
* Visual Studio 2019 Community
* Postman

### `Invoke-IntegrationTests`
#### Alias: `iit`
#### Params: TestSuite e.g. `deployment` | Skip Prompts = `y`, `n`
This script will iterate through an array of paths and run a gradle test suite depending on the parameter provided. It will also prompt to see if the user would like to run the web server in another powershell window.

### `Start-WebServer`
#### Alias: `saws`
This script will navigate to the web server and run node app.js in the same window

### `Read-Report`
#### Alias: `rdrep`
#### Params: Report = `webapp`, `stripe`, `all`
This script will open reports for software gauntlet. 




