FROM mcr.microsoft.com/mssql/server:2022-CU12-ubuntu-20.04

USER root

# Instalar curl y agregar repositorios necesarios
RUN apt-get update && \
    apt-get install -y curl gnupg mssql-tools unixodbc-dev && \
    curl https://packages.microsoft.com/keys/microsoft.asc | apt-key add - && \
    curl https://packages.microsoft.com/config/ubuntu/20.04/prod.list > /etc/apt/sources.list.d/mssql-release.list && \
    apt-get update && \
    ACCEPT_EULA=Y apt-get install -y libodbc1 odbcinst unixodbc unixodbc-dev && \
    apt-get clean

# Configurar el PATH para mssql-tools
ENV PATH="$PATH:/opt/mssql-tools/bin"

# Copiar el script de inicialización
COPY init-db.sh /usr/src/app/init-db.sh

# Dar permisos de ejecución al script
RUN chmod +x /usr/src/app/init-db.sh

USER mssql

# Definir las variables de entorno
ENV ACCEPT_EULA=Y
ENV MSSQL_SA_PASSWORD=Admin#2024sql

# Iniciar SQL Server y luego ejecutar el script de inicialización
ENTRYPOINT /bin/bash -c "/opt/mssql/bin/sqlservr & sleep 15; /usr/src/app/init-db.sh"
