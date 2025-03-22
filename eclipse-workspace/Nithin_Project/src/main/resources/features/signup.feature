Feature: User Signup

@signup
Scenario Outline: Register a new user
Given the user is on homepage
When the user clicks on sign up
And enters a unique "<username>" and "<password>"        
And clicks the sign up button
Then the user should see a success message

Examples:
|username|password|
|Admin123|Test@123|