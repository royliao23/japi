$json = @'
{
    "username": "newtestuser",
    "password": "Test@1234",
    "email": "newtest@example.com",
    "firstName": "Test",
    "lastName": "User"
}
'@

$response = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/register" `
    -Method Post `
    -ContentType "application/json" `
    -Body $json

$response | ConvertTo-Json -Depth 10