APP_NAME="rinha-api"
DOCKER_COMPOSE_FILE="docker-compose.yml"
RELEASE_VERSION="v1.0"  

echo "Realizando build da aplicação"
docker-compose -f $DOCKER_COMPOSE_FILE build

echo "Realizando release da aplicação"
docker tag $APP_NAME:latest $APP_NAME:$RELEASE_VERSION

echo "Realizando deploy da aplicação"
docker-compose -f $DOCKER_COMPOSE_FILE up -d

echo "O processo da Api $APP_NAME:$RELEASE_VERSION foi finzalizado."