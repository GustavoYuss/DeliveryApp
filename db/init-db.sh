#!/bin/bash

/opt/mssql/bin/sqlservr &

sleep 20

/opt/mssql-tools/bin/sqlcmd -S localhost -U development -P Admin#2024sql -Q "CREATE DATABASE DeliveryAppBD"



wait
