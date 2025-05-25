#!/bin/bash

URL="http://localhost:8081/orders"
CONCURRENCY=100
REQUESTS=1000
METHOD="POST"
HEADERS="Content-Type: application/json"
DATA='{ "productId": "833d9239-0049-4d1d-957a-dddafaa3ad18", "quantity": 1 }'

echo "Running load test on $URL with $REQUESTS requests and concurrency $CONCURRENCY..."

oha -n $REQUESTS -c $CONCURRENCY -m $METHOD \
  -H "$HEADERS" \
  -d "$DATA" \
  $URL | tee oha-result.txt

