# Assembleia Geral

Aplicação configurada para executar com java 17.

Necessário ter banco de dados PostgreSQL rodando no localhost:5432.

    Para auxiliar, deixei um arquivo com imagem docker para subir o postgreSQL
    -> docker-compose.yml
    -> https://docs.docker.com/compose/gettingstarted/

####Path do arquivo de configurações
        src/main/resources/application.properties

####Para executar a aplicação via terminal, executar na pasta raíz do projeto
        $ mwnw clean package
        $ cd target
        $ java -jar .\assembleia-0.0.1-SNAPSHOT.jar

####Após compilação, acessar no navegador o link da documentação da API
        http://localhost:8080/swagger-ui/index.html

####Versionamento da API e Paths para os controladores
    .../hello
    .../v1/pauta
    .../v1/usuario
    .../v1/voto

####Como criar uma pauta?
    1. Informar titulo, descrição, tipoUnidadeTempo ("MINUTO","DIA" ou "SEMANA") e quantidadeUnidadeTempo.
    2. A API será responsável por calcular o tempo de término para a votação.

####O que devo fazer para realizar um voto?

    1. Criar um usuário;
    2. Criar uma pauta;
    3. Utilizar os códigos dos registros anteriores e um valor char "S" ou "N".

####Considerações sobre desenvolvimento
    1. Simulação de cache local para as pautas ativas, evitando pesquisas no banco de dados a cada voto;
    2. Retorno da aplicação por padrão retorna um DTO contendo os campos de mensagem e dados, sendo respectivamente
       uma lista de erros e o dado de retorno;
    3. Tarefa agendada para realizar a limpeza da cachê a cada minuto, também podendo resgatar pautas ativas em banco
       quando realizar o deploy da API.

####Melhorias que podem ser feitas
    1. Implementação de testes automatizados;
    2. Separação dos DTOs de requisição e retorno para o Service de Pauta e Usuário;
    3. Verificação e melhorias sobre a implementação do JavaDoc.
    4. Implementar as anotações do Swagger para os campos dos DTOs.