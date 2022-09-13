docker network create --driver bridge postgres-network

docker-compose -f start-postgres.yml up -d
sleep 15
docker-compose -f start-rabbitmq.yml up -d
sleep 5
cd BANTADS-orchestrator
mvn spring-boot:build-image
cd ..
docker-compose -f start-orchestrator.yml up -d
cd BANTADS-auth
mvn spring-boot:build-image
cd ..
cd BANTADS-conta
mvn spring-boot:build-image
cd ..
cd BANTADS-gerente
mvn spring-boot:build-image
cd ..
cd BANTADS-cliente
mvn spring-boot:build-image
cd ..
cd BANTADS-front
docker build -t bantads-front .
cd ..
cd BANTADS-gateway
docker build -t bantads-gateway .
cd ..

docker-compose -f start-services.yml up -d