# Rinha-api

O projeto rinha-api é uma aplicação baseada em Spring, projetada para operar em um ambiente Docker. Este projeto simula um cenário de produção, com várias instâncias da API Spring compartilhando o mesmo banco de dados MySQL e empregando balanceamento de carga. Para distribuir equitativamente as requisições entre as instâncias da API, utilizamos o NGINX como um balanceador de carga, configurado com o algoritmo round-robin. Essa estratégia assegura um tratamento eficiente de todas as requisições recebidas.

## Executar o Projeto Localmente 

1. Inicie o Git Bash se estiver no Windows e apenas faça referência ao arquivo de deploy dessa forma: `./deploy.sh`. Caso esteja no Linux, precisa apenas referenciar o arquivo.

> **Requisito:** É necessário ter o Docker instalado em sua máquina host.
