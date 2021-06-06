curl --location --request POST 'http://localhost:8080/v1/solicitacao/'$1'/decisao' \
--header 'Content-Type: application/json' \
--data-raw '
{
  "decisao": "APROVADO"
}'