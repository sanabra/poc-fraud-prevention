curl --location --request POST 'http://localhost:8080/v1/solicitacao/' \
--header 'Content-Type: application/json' \
--data-raw '
{
  "canal": "APP Banco Digital",
  "chave": "123123234",
  "produto": "PIX"
}'