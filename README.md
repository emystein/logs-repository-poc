# Platform logs API
Expone los logs de la plataforma a través de una API HTTP.


# Arquitectura

Filebeat es una aplicación que hay que instalar y correr en la misma máquina donde corre la app que genera logs.

Filebeat consume el archivo de log y por cada linea del archivo emite un evento a Logstash.

Logstash consume los eventos que recibe de Filebeat y los indexa en un repositorio de eventos de log (MongoDB o Elasticsearch)

Para consultar los eventos de log, el usuario consume la API de esta aplicación, que a su vez consulta el repositorio de eventos de log.


# Setup

## Variables de entorno

`PLATFORM_LOGS_API_PORT`: puerto HTTP de la aplicación, default `6001`

## Logstash

Instalar [Logstash](https://www.elastic.co/guide/en/logstash/current/index.html).

Luego configurar Logstash según el repositorio de eventos de log usado (ver sección `Repositorios` debajo)

Ejecutar Logstash:

```bash
cd $LOGSTASH_HOME
./logstash
```


## Repositorios

La API se puede configurar para consumir información de un repositorio MongoDB o Elasticsearch (excluyentemente).


### Repositorio MongoDB

La base de datos se llama `platform_logs`, y la collection con los logs de la aplicación `app_logs`.


#### Expiración de Documentos

Para mantener reducido el tamaño de la base de datos, configurar un índice Time To Live (TTL) para que expiren los documentos automáticamente luego del tiempo configurado.

En `mongosh`:

```bash
use platform_logs
db.app_log.createIndex({"@timestamp": 1}, {expireAfterSeconds: 259200})
```

Este comando setea TTL para los documentos de la collection `app_log` de 3 días (259200 segundos).

Referencia: https://docs.mongodb.com/manual/core/index-ttl/


#### Logstash
Logstash consume los logs originales y produce eventos que luego envía al repositorio.

1. Instalar el plugin de output [mongodb](https://www.elastic.co/guide/en/logstash/current/plugins-outputs-mongodb.html)

```bash
$LOGSTASH_HOME/bin/logstash-plugin install --version=3.1.5 logstash-output-mongodb
```

NOTA: usar la version `3.1.5` ya que la actual (3.1.6) tiene un [bug](https://github.com/logstash-plugins/logstash-output-mongodb/issues/60)

2. Copiar los archivos de configuración a la instalación de Logstash:

```bash
cp mongodb/logstash/config/pipelines.yml $LOGSTASH_HOME/config
cp mongodb/logstash/config/app.conf $LOGSTASH_HOME/config
```


#### Variables de entorno

`MONGODB_HOST`: default `localhost`

`MONGODB_PORT`: default `27017`


### Repositorio Elasticsearch

#### Variables de entorno

`ELASTICSEARCH_HOST`: default `localhost`

`ELASTICSEARCH_PORT`: default `9200`

`ELASTICSEARCH_USERNAME`: default `elastic`

`ELASTICSEARCH_PASSWORD`: default `changeme`


## Filebeat

Instalar [Filebeat](https://www.elastic.co/guide/en/beats/filebeat/current/index.html) en la máquina que genera el o los archivos de log.

Configurar Filebeat para que envíe los eventos al server de Logstash, escribir en $FILEBEAT_HOME/filebeat.yml el host de Logstash.

Ejecutar Filebeat:

```bash
cd $FILEBEAT_HOME
./filebeat
```

# Desarrollo

## Docker

Construcción de la imagen:

```bash
cd app
gradle bootBuildImage
````

Ejecución:

```bash
docker run flow/platform-logs-api
```


## Ejecución

### Con repositorio MongoDB

Ejecutar el container de MongoDB:
```bash
docker run --name mongodb -p27017:27017 mongo
```

De ser necesario ejecutar `mongosh` en el container:

```bash
docker exec -it mongodb bash
mongo
```

Ejecutar la aplicación:
```bash
cd app
gradle bootRun --args='--spring.profiles.active=mongodb'  
```

### Con repositorio Elasticsearch

Ejecutar la aplicación:
```bash
cd app
gradle bootRun --args='--spring.profiles.active=elasticsearch'
````


## Referencias

https://www.baeldung.com/spring-data-elasticsearch-tutorial

https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#reference

https://spring.io/guides/tutorials/rest/

https://reflectoring.io/spring-boot-web-controller-test/

https://www.baeldung.com/spring-rest-openapi-documentation


# API 

## API Docs

http://localhost:6001/v3/api-docs/

## Swagger UI

http://localhost:6001/swagger-ui.html

