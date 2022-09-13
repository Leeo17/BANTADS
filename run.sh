docker-compose -f start-postgres.yml up -d
sleep 15
docker-compose -f start-rabbitmq.yml up -d
sleep 5
docker-compose -f start-orchestrator.yml up -d
docker-compose -f start-services.yml up -d