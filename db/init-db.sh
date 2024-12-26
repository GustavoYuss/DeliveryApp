#!/bin/bash

/opt/mssql/bin/sqlservr &

# Esperar hasta que SQL Server esté listo
echo "Esperando a que SQL Server esté listo..."
until /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "Admin#2024sql" -Q "SELECT 1" &>/dev/null; do
    echo "SQL Server aún no está listo. Esperando..."
    sleep 10
done

echo "SQL Server está listo. Ejecutando script de inicialización..."

# Crear la base de datos si no existe
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "Admin#2024sql" -Q "
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'DeliveryApp')
BEGIN
    CREATE DATABASE DeliveryApp;
    PRINT 'Database DeliveryApp created';
END
ELSE
    PRINT 'Database DeliveryApp already exists';
"

wait
