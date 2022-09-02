Set-Location tests
$AllTests = Get-ChildItem
Write-Host "Running all unit tests"
Write-Host ""
foreach($Test in $AllTests) {
    node $Test
}
Write-Host ""
Write-Host "All tests finished"