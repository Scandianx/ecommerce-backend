# Ecommerce Backend

Este é o backend de um sistema de ecommerce desenvolvido em Java Spring Boot, utilizando tecnologias como JSON Web Token (JWT), Hibernate, banco de dados PostgreSQL e SendMail para fornecer uma solução robusta e eficiente.

This is the backend for an ecommerce system developed in Java Spring Boot, utilizing technologies such as JSON Web Token (JWT), Hibernate, PostgreSQL database, and SendMail to provide a robust and efficient solution.

## Tecnologias Utilizadas / Technologies Used

- **Java Spring Boot:** Framework de desenvolvimento Java para criar aplicações robustas e escaláveis. / Java development framework for building robust and scalable applications.
- **JSON Web Token (JWT):** Para autenticação segura e autorização de usuários. / For secure user authentication and authorization.
- **Hibernate:** Ferramenta ORM (Object-Relational Mapping) para mapear objetos Java para tabelas em um banco de dados relacional. / ORM (Object-Relational Mapping) tool to map Java objects to tables in a relational database.
- **PostgreSQL:** Banco de dados relacional utilizado para armazenar dados de produtos, usuários e outras informações relevantes do ecommerce. / Relational database used to store product, user, and other relevant ecommerce data.
- **SendMail:** Utilizado para o envio de e-mails transacionais, como confirmações de pedidos e recuperação de senha. / Used for sending transactional emails, such as order confirmations and password recovery.

## Configuração e Execução / Configuration and Execution

1. **Configuração do Banco de Dados / Database Configuration:**
   - Configure as informações de conexão com o banco de dados PostgreSQL no arquivo `application.properties`. / Configure the PostgreSQL database connection details in the `application.properties` file.

2. **Configuração do SendMail / SendMail Configuration:**
   - Insira as configurações de e-mail no arquivo `application.properties` para permitir o envio de e-mails pela aplicação. / Insert email configuration details in the `application.properties` file to enable sending emails from the application.

3. **Execução da Aplicação / Running the Application:**
   - Execute a aplicação utilizando a IDE de sua escolha ou o comando `./mvnw spring-boot:run` no terminal. / Run the application using your preferred IDE or the command `./mvnw spring-boot:run` in the terminal.

4. **Endpoints Disponíveis / Available Endpoints:**
   - Acesse `http://localhost:8080/swagger-ui.html` para visualizar a documentação Swagger com os endpoints disponíveis. / Access `http://localhost:8080/swagger-ui.html` to view the Swagger documentation with available endpoints.

## Contribuição / Contribution

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue para relatar problemas ou propor novas funcionalidades. / Contributions are welcome! Feel free to open an issue to report problems or propose new features.

## Licença / License

Este projeto está sob a licença [MIT](LICENSE). / This project is licensed under the [MIT License](LICENSE).
