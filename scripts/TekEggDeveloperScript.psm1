function Deploy-GitCommit {
    param (
        [string]$SkipPrompts = 'n'
    )

    $CurrentBranch = git branch --show-current
    git status
    Write-Host "This script will add, commit, and push" $CurrentBranch "branch to remote" 
    if ('y' -ne $SkipPrompts) {
        $Continue = Read-Host -Prompt "Continue? (y/n)"
        if ('y' -ne $Continue) {
            Write-Host "Nothing staged. Exiting script" -ForegroundColor Red
            exit
        }
    }


    Write-Host ""
    git add .
    $FilesAddedCount = (git diff --name-only --cached).count
    git status
    Write-Host "Staged" $FilesAddedCount "file(s) to commit"
    $CommitMessage = Read-Host -Prompt "Enter commit message (no quotes) "
    git commit -m $CommitMessage

    Write-Host ""
    $AllBranches = git branch -a
    [bool]$IsBranchRemote = $false
    foreach ($Branch in $AllBranches) {
        if ($Branch.Contains("remotes/origin/${CurrentBranch}")) {
            $IsBranchRemote = $true
            break
        }
    }
    if ($IsBranchRemote) {
        git push
    }
    else {
        git push --set-upstream origin $CurrentBranch
    }
    
}
Set-Alias dpgc Deploy-GitCommit

function Merge-GitDevelop {
    param (
        [string]$SkipPrompts = 'n'
    )

    $CurrentBranch = git branch --show-current

    Write-Host "This script will checkout to develop and pull. Then Merge current branch with develop"
    if ('y' -ne $SkipPrompts) {
        $Continue = Read-Host -Prompt "Continue? (y/n)"
        if ('y' -ne $Continue) {
            Write-Host "No commands sent. Exiting script" -ForegroundColor Red
            exit
        }
    }

    
    Write-Host ""
    Write-Host "Pulling develop into ${CurrentBranch}"
    git pull origin develop
    
    Write-Host ""
    Write-Host "YAY, no merge conflicts!"
    Write-Host ""
    
    git checkout develop
    git pull
    git merge $CurrentBranch
    git push
    
    
}
Set-Alias mgdv Merge-GitDevelop

function Install-DeveloperApplicatons {
    $ChocoInstalled = Test-Path -Path "$env:ProgramData\Chocolatey"
    if (!$ChocoInstalled) {
        try {
            [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; Invoke-Expression ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
        }
        catch {
            Write-Warning "An error has occured please ensure your Execution Policy is set to 'RemoteSigned'."
        }
    }
    else {
        Write-Warning "Chocolatey already installed."
    }
    
    $Packages = 'poshgit', 'openjdk17', 'nodejs', 'mysql', 'python', 'gradle', 'intellijidea-community', 'vscode', 'visualstudio2019community', 'postman'
    
    ForEach ($PackageName in $Packages) {
        choco install $PackageName -y
    }
    
    
}
Set-Alias isdvapp Install-DeveloperApplicatons
function Set-FilePath {
    [string]$CurrentLocation = Get-Location
    $DirectoryArray = $CurrentLocation.Split("\");
    $CurrentDirectory = $DirectoryArray[-1]
    if ($DirectoryArray -match "tekegg") {
        while ($CurrentDirectory -ne "tekegg") {
            Set-Location ../
            $CurrentLocation = Get-Location
            $DirectoryArray = $CurrentLocation.Split("\");
            $CurrentDirectory = $DirectoryArray[-1]
        }
    }
    else {
        Write-Host "Unable to run script not in tekegg directory." -ForegroundColor Red
        exit
    }
}

function Invoke-IntegrationTests {
    Param(
        [string]$TestSuite = "deployment",
        [string]$StartServer = ''
    )
    [string]$CurrentLocation = Get-Location
    $AllTestPaths = ".\application\web\test\", ".\service\stripe\test\"
    Set-FilePath

    if ('y' -eq $StartServer) {
        Start-Process powershell -WorkingDirectory ".\application\web\source\" -ArgumentList "npm start"
        Start-Sleep -Seconds 3
    }
    elseif ('' -eq $StartServer) {
        $StartServer = Read-Host -Prompt "Would you like to start the webserver? (y/n)"
        if ('y' -eq $StartServer) {
            Start-Process powershell -WorkingDirectory ".\application\web\source\" -ArgumentList "npm start"
            Start-Sleep -Seconds 3
        }
    }
    
    foreach ($TestPath in $AllTestPaths) {
        Write-Host ""
        Write-Host "Running ${TestSuite} tests in ${TestPath}"
        Set-Location $TestPath
        .\gradlew clean $TestSuite
        Set-Location ../../../
    }
}
Set-Alias iit Invoke-IntegrationTests

function Start-WebServer {
    $ServerPath = ".\application\web\source\"
    Set-Location $ServerPath
    npm start
}
Set-Alias saws Start-WebServer

function Read-Report {
    Param (
        $Report = "all"
    )
    $AllReportsPath = ".\application\web\test\build\reports\tests\", ".\service\stripe\test\build\reports\tests"

    function Open-Report {
        param (
            $Path
        )
        Set-FilePath
        $ReportSuite = Get-ChildItem $Path | Select-Object -First 1
        try { Start-Process "${Path}\${ReportSuite}\index.html" | Write-Host "Report Found ${Path}\${ReportSuite}" -ForegroundColor Green }
        catch { Write-Error -Message "No Report found in ${Path}\${ReportSuite}\index.html" -Category ResourceUnavailable }
    }

    if ("all" -like $Report) {
        foreach ($ReportPath in $AllReportsPath) {
            Open-Report $ReportPath
        }
    }
    elseif ("webapp" -like $Report) {
        Open-Report $AllReportsPath[0]
    }
    elseif ("stripe" -like $Report) {
        Open-Report $AllReportsPath[1]
    }
    else {
        Write-Error -Message "Error: No Report found for ${Report}" -Category InvalidArgument
    }
}
Set-Alias rdrep Read-Report

Export-ModuleMember -Function Deploy-GitCommit, Merge-GitDevelop, Start-WebServer, Install-DeveloperApplicatons, Invoke-IntegrationTests, Start-WebServer, Read-Report -Alias *

